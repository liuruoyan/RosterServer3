import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumEmpTypes } from 'app/entities/enum-emp-type/enum-emp-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-emp-type.reducer';
import { IEnumEmpType } from 'app/shared/model/enum-emp-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumEmpTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumEmpTypeUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumEmpTypeUpdate extends React.Component<IEnumEmpTypeUpdateProps, IEnumEmpTypeUpdateState> {
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

    this.props.getEnumEmpTypes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumEmpTypeEntity } = this.props;
      const entity = {
        ...enumEmpTypeEntity,
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
    this.props.history.push('/entity/enum-emp-type');
  };

  render() {
    const { enumEmpTypeEntity, enumEmpTypes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumEmpType.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumEmpType.home.createOrEditLabel">Create or edit a EnumEmpType</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumEmpTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-emp-type-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-emp-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-emp-type-valuez">
                    <Translate contentKey="rosterServer3App.enumEmpType.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-emp-type-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-emp-type-orderz">
                    <Translate contentKey="rosterServer3App.enumEmpType.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-emp-type-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-emp-type-tenentCode">
                    <Translate contentKey="rosterServer3App.enumEmpType.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-emp-type-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-emp-type-parent">
                    <Translate contentKey="rosterServer3App.enumEmpType.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-emp-type-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumEmpTypes
                      ? enumEmpTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-emp-type" replace color="info">
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
  enumEmpTypes: storeState.enumEmpType.entities,
  enumEmpTypeEntity: storeState.enumEmpType.entity,
  loading: storeState.enumEmpType.loading,
  updating: storeState.enumEmpType.updating,
  updateSuccess: storeState.enumEmpType.updateSuccess
});

const mapDispatchToProps = {
  getEnumEmpTypes,
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
)(EnumEmpTypeUpdate);
