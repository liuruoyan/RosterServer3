import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumSsStatuses } from 'app/entities/enum-ss-status/enum-ss-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-ss-status.reducer';
import { IEnumSsStatus } from 'app/shared/model/enum-ss-status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumSsStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumSsStatusUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumSsStatusUpdate extends React.Component<IEnumSsStatusUpdateProps, IEnumSsStatusUpdateState> {
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

    this.props.getEnumSsStatuses();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumSsStatusEntity } = this.props;
      const entity = {
        ...enumSsStatusEntity,
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
    this.props.history.push('/entity/enum-ss-status');
  };

  render() {
    const { enumSsStatusEntity, enumSsStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumSsStatus.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumSsStatus.home.createOrEditLabel">Create or edit a EnumSsStatus</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumSsStatusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-ss-status-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-ss-status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-ss-status-valuez">
                    <Translate contentKey="rosterServer3App.enumSsStatus.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-ss-status-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-ss-status-orderz">
                    <Translate contentKey="rosterServer3App.enumSsStatus.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-ss-status-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-ss-status-tenentCode">
                    <Translate contentKey="rosterServer3App.enumSsStatus.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-ss-status-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-ss-status-parent">
                    <Translate contentKey="rosterServer3App.enumSsStatus.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-ss-status-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumSsStatuses
                      ? enumSsStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-ss-status" replace color="info">
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
  enumSsStatuses: storeState.enumSsStatus.entities,
  enumSsStatusEntity: storeState.enumSsStatus.entity,
  loading: storeState.enumSsStatus.loading,
  updating: storeState.enumSsStatus.updating,
  updateSuccess: storeState.enumSsStatus.updateSuccess
});

const mapDispatchToProps = {
  getEnumSsStatuses,
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
)(EnumSsStatusUpdate);
