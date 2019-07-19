import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumMaritalStatuses } from 'app/entities/enum-marital-status/enum-marital-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-marital-status.reducer';
import { IEnumMaritalStatus } from 'app/shared/model/enum-marital-status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumMaritalStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumMaritalStatusUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumMaritalStatusUpdate extends React.Component<IEnumMaritalStatusUpdateProps, IEnumMaritalStatusUpdateState> {
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

    this.props.getEnumMaritalStatuses();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumMaritalStatusEntity } = this.props;
      const entity = {
        ...enumMaritalStatusEntity,
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
    this.props.history.push('/entity/enum-marital-status');
  };

  render() {
    const { enumMaritalStatusEntity, enumMaritalStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumMaritalStatus.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumMaritalStatus.home.createOrEditLabel">
                Create or edit a EnumMaritalStatus
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumMaritalStatusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-marital-status-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-marital-status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-marital-status-valuez">
                    <Translate contentKey="rosterServer3App.enumMaritalStatus.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-marital-status-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-marital-status-orderz">
                    <Translate contentKey="rosterServer3App.enumMaritalStatus.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-marital-status-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-marital-status-tenentCode">
                    <Translate contentKey="rosterServer3App.enumMaritalStatus.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-marital-status-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-marital-status-parent">
                    <Translate contentKey="rosterServer3App.enumMaritalStatus.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-marital-status-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumMaritalStatuses
                      ? enumMaritalStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-marital-status" replace color="info">
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
  enumMaritalStatuses: storeState.enumMaritalStatus.entities,
  enumMaritalStatusEntity: storeState.enumMaritalStatus.entity,
  loading: storeState.enumMaritalStatus.loading,
  updating: storeState.enumMaritalStatus.updating,
  updateSuccess: storeState.enumMaritalStatus.updateSuccess
});

const mapDispatchToProps = {
  getEnumMaritalStatuses,
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
)(EnumMaritalStatusUpdate);
