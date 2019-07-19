import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumAccountTypes } from 'app/entities/enum-account-type/enum-account-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-account-type.reducer';
import { IEnumAccountType } from 'app/shared/model/enum-account-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumAccountTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumAccountTypeUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumAccountTypeUpdate extends React.Component<IEnumAccountTypeUpdateProps, IEnumAccountTypeUpdateState> {
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

    this.props.getEnumAccountTypes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumAccountTypeEntity } = this.props;
      const entity = {
        ...enumAccountTypeEntity,
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
    this.props.history.push('/entity/enum-account-type');
  };

  render() {
    const { enumAccountTypeEntity, enumAccountTypes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumAccountType.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumAccountType.home.createOrEditLabel">Create or edit a EnumAccountType</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumAccountTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-account-type-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-account-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-account-type-valuez">
                    <Translate contentKey="rosterServer3App.enumAccountType.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-account-type-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-account-type-orderz">
                    <Translate contentKey="rosterServer3App.enumAccountType.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-account-type-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-account-type-tenentCode">
                    <Translate contentKey="rosterServer3App.enumAccountType.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-account-type-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-account-type-parent">
                    <Translate contentKey="rosterServer3App.enumAccountType.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-account-type-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumAccountTypes
                      ? enumAccountTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-account-type" replace color="info">
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
  enumAccountTypes: storeState.enumAccountType.entities,
  enumAccountTypeEntity: storeState.enumAccountType.entity,
  loading: storeState.enumAccountType.loading,
  updating: storeState.enumAccountType.updating,
  updateSuccess: storeState.enumAccountType.updateSuccess
});

const mapDispatchToProps = {
  getEnumAccountTypes,
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
)(EnumAccountTypeUpdate);
