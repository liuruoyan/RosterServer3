import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumContractType } from 'app/shared/model/enum-contract-type.model';
import { getEntities as getEnumContractTypes } from 'app/entities/enum-contract-type/enum-contract-type.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IContractUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IContractUpdateState {
  isNew: boolean;
  contractTypeId: string;
  empId: string;
}

export class ContractUpdate extends React.Component<IContractUpdateProps, IContractUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      contractTypeId: '0',
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

    this.props.getEnumContractTypes();
    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { contractEntity } = this.props;
      const entity = {
        ...contractEntity,
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
    this.props.history.push('/entity/contract');
  };

  render() {
    const { contractEntity, enumContractTypes, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.contract.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.contract.home.createOrEditLabel">Create or edit a Contract</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : contractEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="contract-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="contract-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="contract-code">
                    <Translate contentKey="rosterServer3App.contract.code">Code</Translate>
                  </Label>
                  <AvField id="contract-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.contract.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="startDateLabel" for="contract-startDate">
                    <Translate contentKey="rosterServer3App.contract.startDate">Start Date</Translate>
                  </Label>
                  <AvField id="contract-startDate" type="date" className="form-control" name="startDate" />
                  <UncontrolledTooltip target="startDateLabel">
                    <Translate contentKey="rosterServer3App.contract.help.startDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="endDateLabel" for="contract-endDate">
                    <Translate contentKey="rosterServer3App.contract.endDate">End Date</Translate>
                  </Label>
                  <AvField id="contract-endDate" type="date" className="form-control" name="endDate" />
                  <UncontrolledTooltip target="endDateLabel">
                    <Translate contentKey="rosterServer3App.contract.help.endDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="contract-email">
                    <Translate contentKey="rosterServer3App.contract.email">Email</Translate>
                  </Label>
                  <AvField id="contract-email" type="text" name="email" />
                  <UncontrolledTooltip target="emailLabel">
                    <Translate contentKey="rosterServer3App.contract.help.email" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="workTelLabel" for="contract-workTel">
                    <Translate contentKey="rosterServer3App.contract.workTel">Work Tel</Translate>
                  </Label>
                  <AvField id="contract-workTel" type="text" name="workTel" />
                  <UncontrolledTooltip target="workTelLabel">
                    <Translate contentKey="rosterServer3App.contract.help.workTel" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="probationEndDayLabel" for="contract-probationEndDay">
                    <Translate contentKey="rosterServer3App.contract.probationEndDay">Probation End Day</Translate>
                  </Label>
                  <AvField id="contract-probationEndDay" type="date" className="form-control" name="probationEndDay" />
                  <UncontrolledTooltip target="probationEndDayLabel">
                    <Translate contentKey="rosterServer3App.contract.help.probationEndDay" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="probationLengthLabel" for="contract-probationLength">
                    <Translate contentKey="rosterServer3App.contract.probationLength">Probation Length</Translate>
                  </Label>
                  <AvField id="contract-probationLength" type="string" className="form-control" name="probationLength" />
                  <UncontrolledTooltip target="probationLengthLabel">
                    <Translate contentKey="rosterServer3App.contract.help.probationLength" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="otherLabel" for="contract-other">
                    <Translate contentKey="rosterServer3App.contract.other">Other</Translate>
                  </Label>
                  <AvField id="contract-other" type="text" name="other" />
                  <UncontrolledTooltip target="otherLabel">
                    <Translate contentKey="rosterServer3App.contract.help.other" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="contract-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.contract.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.contract.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="contract-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.contract.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.contract.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="contract-contractType">
                    <Translate contentKey="rosterServer3App.contract.contractType">Contract Type</Translate>
                  </Label>
                  <AvInput id="contract-contractType" type="select" className="form-control" name="contractTypeId">
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
                <AvGroup>
                  <Label for="contract-emp">
                    <Translate contentKey="rosterServer3App.contract.emp">Emp</Translate>
                  </Label>
                  <AvInput id="contract-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/contract" replace color="info">
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
  employees: storeState.employee.entities,
  contractEntity: storeState.contract.entity,
  loading: storeState.contract.loading,
  updating: storeState.contract.updating,
  updateSuccess: storeState.contract.updateSuccess
});

const mapDispatchToProps = {
  getEnumContractTypes,
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
)(ContractUpdate);
