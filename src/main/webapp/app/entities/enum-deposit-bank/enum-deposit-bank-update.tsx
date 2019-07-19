import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumDepositBanks } from 'app/entities/enum-deposit-bank/enum-deposit-bank.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-deposit-bank.reducer';
import { IEnumDepositBank } from 'app/shared/model/enum-deposit-bank.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumDepositBankUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumDepositBankUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumDepositBankUpdate extends React.Component<IEnumDepositBankUpdateProps, IEnumDepositBankUpdateState> {
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

    this.props.getEnumDepositBanks();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumDepositBankEntity } = this.props;
      const entity = {
        ...enumDepositBankEntity,
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
    this.props.history.push('/entity/enum-deposit-bank');
  };

  render() {
    const { enumDepositBankEntity, enumDepositBanks, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumDepositBank.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumDepositBank.home.createOrEditLabel">Create or edit a EnumDepositBank</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumDepositBankEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-deposit-bank-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-deposit-bank-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-deposit-bank-valuez">
                    <Translate contentKey="rosterServer3App.enumDepositBank.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-deposit-bank-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-deposit-bank-orderz">
                    <Translate contentKey="rosterServer3App.enumDepositBank.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-deposit-bank-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-deposit-bank-tenentCode">
                    <Translate contentKey="rosterServer3App.enumDepositBank.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-deposit-bank-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-deposit-bank-parent">
                    <Translate contentKey="rosterServer3App.enumDepositBank.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-deposit-bank-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumDepositBanks
                      ? enumDepositBanks.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-deposit-bank" replace color="info">
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
  enumDepositBanks: storeState.enumDepositBank.entities,
  enumDepositBankEntity: storeState.enumDepositBank.entity,
  loading: storeState.enumDepositBank.loading,
  updating: storeState.enumDepositBank.updating,
  updateSuccess: storeState.enumDepositBank.updateSuccess
});

const mapDispatchToProps = {
  getEnumDepositBanks,
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
)(EnumDepositBankUpdate);
