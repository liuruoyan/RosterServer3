import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumEmpStatuses } from 'app/entities/enum-emp-status/enum-emp-status.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-emp-status.reducer';
import { IEnumEmpStatus } from 'app/shared/model/enum-emp-status.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumEmpStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumEmpStatusUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumEmpStatusUpdate extends React.Component<IEnumEmpStatusUpdateProps, IEnumEmpStatusUpdateState> {
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

    this.props.getEnumEmpStatuses();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumEmpStatusEntity } = this.props;
      const entity = {
        ...enumEmpStatusEntity,
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
    this.props.history.push('/entity/enum-emp-status');
  };

  render() {
    const { enumEmpStatusEntity, enumEmpStatuses, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumEmpStatus.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumEmpStatus.home.createOrEditLabel">Create or edit a EnumEmpStatus</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumEmpStatusEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-emp-status-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-emp-status-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-emp-status-valuez">
                    <Translate contentKey="rosterServer3App.enumEmpStatus.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-emp-status-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-emp-status-orderz">
                    <Translate contentKey="rosterServer3App.enumEmpStatus.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-emp-status-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-emp-status-tenentCode">
                    <Translate contentKey="rosterServer3App.enumEmpStatus.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-emp-status-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-emp-status-parent">
                    <Translate contentKey="rosterServer3App.enumEmpStatus.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-emp-status-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumEmpStatuses
                      ? enumEmpStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-emp-status" replace color="info">
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
  enumEmpStatuses: storeState.enumEmpStatus.entities,
  enumEmpStatusEntity: storeState.enumEmpStatus.entity,
  loading: storeState.enumEmpStatus.loading,
  updating: storeState.enumEmpStatus.updating,
  updateSuccess: storeState.enumEmpStatus.updateSuccess
});

const mapDispatchToProps = {
  getEnumEmpStatuses,
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
)(EnumEmpStatusUpdate);
