import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumIdTypes } from 'app/entities/enum-id-type/enum-id-type.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-id-type.reducer';
import { IEnumIdType } from 'app/shared/model/enum-id-type.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumIdTypeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumIdTypeUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumIdTypeUpdate extends React.Component<IEnumIdTypeUpdateProps, IEnumIdTypeUpdateState> {
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

    this.props.getEnumIdTypes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumIdTypeEntity } = this.props;
      const entity = {
        ...enumIdTypeEntity,
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
    this.props.history.push('/entity/enum-id-type');
  };

  render() {
    const { enumIdTypeEntity, enumIdTypes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumIdType.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumIdType.home.createOrEditLabel">Create or edit a EnumIdType</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumIdTypeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-id-type-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-id-type-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-id-type-valuez">
                    <Translate contentKey="rosterServer3App.enumIdType.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-id-type-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-id-type-orderz">
                    <Translate contentKey="rosterServer3App.enumIdType.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-id-type-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-id-type-tenentCode">
                    <Translate contentKey="rosterServer3App.enumIdType.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-id-type-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-id-type-parent">
                    <Translate contentKey="rosterServer3App.enumIdType.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-id-type-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumIdTypes
                      ? enumIdTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-id-type" replace color="info">
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
  enumIdTypes: storeState.enumIdType.entities,
  enumIdTypeEntity: storeState.enumIdType.entity,
  loading: storeState.enumIdType.loading,
  updating: storeState.enumIdType.updating,
  updateSuccess: storeState.enumIdType.updateSuccess
});

const mapDispatchToProps = {
  getEnumIdTypes,
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
)(EnumIdTypeUpdate);
