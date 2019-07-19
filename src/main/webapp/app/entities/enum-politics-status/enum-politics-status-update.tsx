import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumPoliticsStatuses } from 'app/entities/enum-politics-status/enum-politics-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-politics-status.reducer';
import { IEnumPoliticsStatus } from 'app/shared/model/enum-politics-status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumPoliticsStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumPoliticsStatusUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumPoliticsStatusUpdate extends React.Component<IEnumPoliticsStatusUpdateProps, IEnumPoliticsStatusUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      parentId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEnumPoliticsStatuses();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumPoliticsStatusEntity } = this.props;
      const entity = {
        ...enumPoliticsStatusEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/enum-politics-status');
  };

  render() {
    const { enumPoliticsStatusEntity, enumPoliticsStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumPoliticsStatus.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumPoliticsStatus.home.createOrEditLabel">
                Create or edit a EnumPoliticsStatus
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumPoliticsStatusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-politics-status-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-politics-status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-politics-status-valuez">
                    <Translate contentKey="rosterServer3App.enumPoliticsStatus.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-politics-status-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-politics-status-orderz">
                    <Translate contentKey="rosterServer3App.enumPoliticsStatus.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-politics-status-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-politics-status-tenentCode">
                    <Translate contentKey="rosterServer3App.enumPoliticsStatus.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-politics-status-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-politics-status-parent">
                    <Translate contentKey="rosterServer3App.enumPoliticsStatus.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-politics-status-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumPoliticsStatuses
                      ? enumPoliticsStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-politics-status" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  enumPoliticsStatuses: storeState.enumPoliticsStatus.entities,
  enumPoliticsStatusEntity: storeState.enumPoliticsStatus.entity,
  loading: storeState.enumPoliticsStatus.loading,
  updating: storeState.enumPoliticsStatus.updating,
  updateSuccess: storeState.enumPoliticsStatus.updateSuccess
});

const mapDispatchToProps = {
  getEnumPoliticsStatuses,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPoliticsStatusUpdate);
