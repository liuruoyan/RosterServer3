import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumEmpTaxAreas } from 'app/entities/enum-emp-tax-area/enum-emp-tax-area.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-emp-tax-area.reducer';
import { IEnumEmpTaxArea } from 'app/shared/model/enum-emp-tax-area.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumEmpTaxAreaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumEmpTaxAreaUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumEmpTaxAreaUpdate extends React.Component<IEnumEmpTaxAreaUpdateProps, IEnumEmpTaxAreaUpdateState> {
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

    this.props.getEnumEmpTaxAreas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumEmpTaxAreaEntity } = this.props;
      const entity = {
        ...enumEmpTaxAreaEntity,
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
    this.props.history.push('/entity/enum-emp-tax-area');
  };

  render() {
    const { enumEmpTaxAreaEntity, enumEmpTaxAreas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumEmpTaxArea.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumEmpTaxArea.home.createOrEditLabel">Create or edit a EnumEmpTaxArea</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumEmpTaxAreaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-emp-tax-area-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-emp-tax-area-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-emp-tax-area-valuez">
                    <Translate contentKey="rosterServer3App.enumEmpTaxArea.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-emp-tax-area-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-emp-tax-area-orderz">
                    <Translate contentKey="rosterServer3App.enumEmpTaxArea.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-emp-tax-area-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-emp-tax-area-tenentCode">
                    <Translate contentKey="rosterServer3App.enumEmpTaxArea.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-emp-tax-area-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-emp-tax-area-parent">
                    <Translate contentKey="rosterServer3App.enumEmpTaxArea.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-emp-tax-area-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumEmpTaxAreas
                      ? enumEmpTaxAreas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-emp-tax-area" replace color="info">
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
  enumEmpTaxAreas: storeState.enumEmpTaxArea.entities,
  enumEmpTaxAreaEntity: storeState.enumEmpTaxArea.entity,
  loading: storeState.enumEmpTaxArea.loading,
  updating: storeState.enumEmpTaxArea.updating,
  updateSuccess: storeState.enumEmpTaxArea.updateSuccess
});

const mapDispatchToProps = {
  getEnumEmpTaxAreas,
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
)(EnumEmpTaxAreaUpdate);
