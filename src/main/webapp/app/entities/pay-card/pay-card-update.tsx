import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pay-card.reducer';
import { IPayCard } from 'app/shared/model/pay-card.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPayCardUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPayCardUpdateState {
  isNew: boolean;
  empId: string;
}

export class PayCardUpdate extends React.Component<IPayCardUpdateProps, IPayCardUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      empId: '0',
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

    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { payCardEntity } = this.props;
      const entity = {
        ...payCardEntity,
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
    this.props.history.push('/entity/pay-card');
  };

  render() {
    const { payCardEntity, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.payCard.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.payCard.home.createOrEditLabel">Create or edit a PayCard</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : payCardEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pay-card-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pay-card-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="pay-card-code">
                    <Translate contentKey="rosterServer3App.payCard.code">Code</Translate>
                  </Label>
                  <AvField id="pay-card-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="branchLabel" for="pay-card-branch">
                    <Translate contentKey="rosterServer3App.payCard.branch">Branch</Translate>
                  </Label>
                  <AvField id="pay-card-branch" type="text" name="branch" />
                  <UncontrolledTooltip target="branchLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.branch" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="accountNameLabel" for="pay-card-accountName">
                    <Translate contentKey="rosterServer3App.payCard.accountName">Account Name</Translate>
                  </Label>
                  <AvField id="pay-card-accountName" type="text" name="accountName" />
                  <UncontrolledTooltip target="accountNameLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.accountName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bankAccountLabel" for="pay-card-bankAccount">
                    <Translate contentKey="rosterServer3App.payCard.bankAccount">Bank Account</Translate>
                  </Label>
                  <AvField id="pay-card-bankAccount" type="text" name="bankAccount" />
                  <UncontrolledTooltip target="bankAccountLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.bankAccount" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="depositBankLabel" for="pay-card-depositBank">
                    <Translate contentKey="rosterServer3App.payCard.depositBank">Deposit Bank</Translate>
                  </Label>
                  <AvField id="pay-card-depositBank" type="text" name="depositBank" />
                  <UncontrolledTooltip target="depositBankLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.depositBank" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="pay-card-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.payCard.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="pay-card-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.payCard.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.payCard.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="pay-card-emp">
                    <Translate contentKey="rosterServer3App.payCard.emp">Emp</Translate>
                  </Label>
                  <AvInput id="pay-card-emp" type="select" className="form-control" name="empId">
                    <option value="" key="0" />
                    {employees
                      ? employees.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pay-card" replace color="info">
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
  employees: storeState.employee.entities,
  payCardEntity: storeState.payCard.entity,
  loading: storeState.payCard.loading,
  updating: storeState.payCard.updating,
  updateSuccess: storeState.payCard.updateSuccess
});

const mapDispatchToProps = {
  getEmployees,
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
)(PayCardUpdate);
