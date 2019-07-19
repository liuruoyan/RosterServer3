import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumContractTypes } from 'app/entities/enum-contract-type/enum-contract-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-contract-type.reducer';
import { IEnumContractType } from 'app/shared/model/enum-contract-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumContractTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumContractTypeUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumContractTypeUpdate extends React.Component<IEnumContractTypeUpdateProps, IEnumContractTypeUpdateState> {
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

    this.props.getEnumContractTypes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumContractTypeEntity } = this.props;
      const entity = {
        ...enumContractTypeEntity,
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
    this.props.history.push('/entity/enum-contract-type');
  };

  render() {
    const { enumContractTypeEntity, enumContractTypes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumContractType.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumContractType.home.createOrEditLabel">Create or edit a EnumContractType</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumContractTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-contract-type-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-contract-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-contract-type-valuez">
                    <Translate contentKey="rosterServer3App.enumContractType.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-contract-type-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-contract-type-orderz">
                    <Translate contentKey="rosterServer3App.enumContractType.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-contract-type-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-contract-type-tenentCode">
                    <Translate contentKey="rosterServer3App.enumContractType.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-contract-type-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-contract-type-parent">
                    <Translate contentKey="rosterServer3App.enumContractType.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-contract-type-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumContractTypes
                      ? enumContractTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-contract-type" replace color="info">
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
  enumContractTypes: storeState.enumContractType.entities,
  enumContractTypeEntity: storeState.enumContractType.entity,
  loading: storeState.enumContractType.loading,
  updating: storeState.enumContractType.updating,
  updateSuccess: storeState.enumContractType.updateSuccess
});

const mapDispatchToProps = {
  getEnumContractTypes,
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
)(EnumContractTypeUpdate);
