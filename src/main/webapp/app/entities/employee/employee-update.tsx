import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumEmpStatus } from 'app/shared/model/enum-emp-status.model';
import { getEntities as getEnumEmpStatuses } from 'app/entities/enum-emp-status/enum-emp-status.reducer';
import { IEnumIdType } from 'app/shared/model/enum-id-type.model';
import { getEntities as getEnumIdTypes } from 'app/entities/enum-id-type/enum-id-type.reducer';
import { IEnumContractType } from 'app/shared/model/enum-contract-type.model';
import { getEntities as getEnumContractTypes } from 'app/entities/enum-contract-type/enum-contract-type.reducer';
import { IEnumEmpType } from 'app/shared/model/enum-emp-type.model';
import { getEntities as getEnumEmpTypes } from 'app/entities/enum-emp-type/enum-emp-type.reducer';
import { IEnumGender } from 'app/shared/model/enum-gender.model';
import { getEntities as getEnumGenders } from 'app/entities/enum-gender/enum-gender.reducer';
import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEmployeeUpdateState {
  isNew: boolean;
  statusId: string;
  idTypeId: string;
  contractTypeId: string;
  empTypeId: string;
  genderId: string;
}

export class EmployeeUpdate extends React.Component<IEmployeeUpdateProps, IEmployeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      statusId: '0',
      idTypeId: '0',
      contractTypeId: '0',
      empTypeId: '0',
      genderId: '0',
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
    this.props.getEnumIdTypes();
    this.props.getEnumContractTypes();
    this.props.getEnumEmpTypes();
    this.props.getEnumGenders();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { employeeEntity } = this.props;
      const entity = {
        ...employeeEntity,
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
    this.props.history.push('/entity/employee');
  };

  render() {
    const { employeeEntity, enumEmpStatuses, enumIdTypes, enumContractTypes, enumEmpTypes, enumGenders, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.employee.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.employee.home.createOrEditLabel">Create or edit a Employee</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : employeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="employee-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="employee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="employee-code">
                    <Translate contentKey="rosterServer3App.employee.code">Code</Translate>
                  </Label>
                  <AvField id="employee-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.employee.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="employee-name">
                    <Translate contentKey="rosterServer3App.employee.name">Name</Translate>
                  </Label>
                  <AvField
                    id="employee-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                  <UncontrolledTooltip target="nameLabel">
                    <Translate contentKey="rosterServer3App.employee.help.name" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="idNumberLabel" for="employee-idNumber">
                    <Translate contentKey="rosterServer3App.employee.idNumber">Id Number</Translate>
                  </Label>
                  <AvField
                    id="employee-idNumber"
                    type="text"
                    name="idNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                  <UncontrolledTooltip target="idNumberLabel">
                    <Translate contentKey="rosterServer3App.employee.help.idNumber" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="employee-phone">
                    <Translate contentKey="rosterServer3App.employee.phone">Phone</Translate>
                  </Label>
                  <AvField
                    id="employee-phone"
                    type="text"
                    name="phone"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="rosterServer3App.employee.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="hireDateLabel" for="employee-hireDate">
                    <Translate contentKey="rosterServer3App.employee.hireDate">Hire Date</Translate>
                  </Label>
                  <AvField
                    id="employee-hireDate"
                    type="date"
                    className="form-control"
                    name="hireDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                  <UncontrolledTooltip target="hireDateLabel">
                    <Translate contentKey="rosterServer3App.employee.help.hireDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="jobGradeLabel" for="employee-jobGrade">
                    <Translate contentKey="rosterServer3App.employee.jobGrade">Job Grade</Translate>
                  </Label>
                  <AvField id="employee-jobGrade" type="text" name="jobGrade" />
                  <UncontrolledTooltip target="jobGradeLabel">
                    <Translate contentKey="rosterServer3App.employee.help.jobGrade" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="positionLabel" for="employee-position">
                    <Translate contentKey="rosterServer3App.employee.position">Position</Translate>
                  </Label>
                  <AvField id="employee-position" type="text" name="position" />
                  <UncontrolledTooltip target="positionLabel">
                    <Translate contentKey="rosterServer3App.employee.help.position" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="jobLabel" for="employee-job">
                    <Translate contentKey="rosterServer3App.employee.job">Job</Translate>
                  </Label>
                  <AvField id="employee-job" type="text" name="job" />
                  <UncontrolledTooltip target="jobLabel">
                    <Translate contentKey="rosterServer3App.employee.help.job" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="deptNameLabel" for="employee-deptName">
                    <Translate contentKey="rosterServer3App.employee.deptName">Dept Name</Translate>
                  </Label>
                  <AvField id="employee-deptName" type="text" name="deptName" />
                  <UncontrolledTooltip target="deptNameLabel">
                    <Translate contentKey="rosterServer3App.employee.help.deptName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="empNoLabel" for="employee-empNo">
                    <Translate contentKey="rosterServer3App.employee.empNo">Emp No</Translate>
                  </Label>
                  <AvField id="employee-empNo" type="text" name="empNo" />
                  <UncontrolledTooltip target="empNoLabel">
                    <Translate contentKey="rosterServer3App.employee.help.empNo" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="seniorityLabel" for="employee-seniority">
                    <Translate contentKey="rosterServer3App.employee.seniority">Seniority</Translate>
                  </Label>
                  <AvField id="employee-seniority" type="string" className="form-control" name="seniority" />
                  <UncontrolledTooltip target="seniorityLabel">
                    <Translate contentKey="rosterServer3App.employee.help.seniority" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="contractorLabel" for="employee-contractor">
                    <Translate contentKey="rosterServer3App.employee.contractor">Contractor</Translate>
                  </Label>
                  <AvField id="employee-contractor" type="text" name="contractor" />
                  <UncontrolledTooltip target="contractorLabel">
                    <Translate contentKey="rosterServer3App.employee.help.contractor" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="birthTypeLabel" for="employee-birthType">
                    <Translate contentKey="rosterServer3App.employee.birthType">Birth Type</Translate>
                  </Label>
                  <AvInput
                    id="employee-birthType"
                    type="select"
                    className="form-control"
                    name="birthType"
                    value={(!isNew && employeeEntity.birthType) || 'LUNAR'}
                  >
                    <option value="LUNAR">{translate('rosterServer3App.BirthType.LUNAR')}</option>
                    <option value="CALENDAR">{translate('rosterServer3App.BirthType.CALENDAR')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="birthTypeLabel">
                    <Translate contentKey="rosterServer3App.employee.help.birthType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="birthdayLabel" for="employee-birthday">
                    <Translate contentKey="rosterServer3App.employee.birthday">Birthday</Translate>
                  </Label>
                  <AvField id="employee-birthday" type="date" className="form-control" name="birthday" />
                  <UncontrolledTooltip target="birthdayLabel">
                    <Translate contentKey="rosterServer3App.employee.help.birthday" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="workLocLabel" for="employee-workLoc">
                    <Translate contentKey="rosterServer3App.employee.workLoc">Work Loc</Translate>
                  </Label>
                  <AvField id="employee-workLoc" type="text" name="workLoc" />
                  <UncontrolledTooltip target="workLocLabel">
                    <Translate contentKey="rosterServer3App.employee.help.workLoc" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="contactAddrLabel" for="employee-contactAddr">
                    <Translate contentKey="rosterServer3App.employee.contactAddr">Contact Addr</Translate>
                  </Label>
                  <AvField id="employee-contactAddr" type="text" name="contactAddr" />
                  <UncontrolledTooltip target="contactAddrLabel">
                    <Translate contentKey="rosterServer3App.employee.help.contactAddr" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nationalityLabel" for="employee-nationality">
                    <Translate contentKey="rosterServer3App.employee.nationality">Nationality</Translate>
                  </Label>
                  <AvField id="employee-nationality" type="text" name="nationality" />
                  <UncontrolledTooltip target="nationalityLabel">
                    <Translate contentKey="rosterServer3App.employee.help.nationality" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="firstNameLabel" for="employee-firstName">
                    <Translate contentKey="rosterServer3App.employee.firstName">First Name</Translate>
                  </Label>
                  <AvField id="employee-firstName" type="text" name="firstName" />
                  <UncontrolledTooltip target="firstNameLabel">
                    <Translate contentKey="rosterServer3App.employee.help.firstName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="lastNameLabel" for="employee-lastName">
                    <Translate contentKey="rosterServer3App.employee.lastName">Last Name</Translate>
                  </Label>
                  <AvField id="employee-lastName" type="text" name="lastName" />
                  <UncontrolledTooltip target="lastNameLabel">
                    <Translate contentKey="rosterServer3App.employee.help.lastName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="othersLabel" for="employee-others">
                    <Translate contentKey="rosterServer3App.employee.others">Others</Translate>
                  </Label>
                  <AvField id="employee-others" type="text" name="others" />
                  <UncontrolledTooltip target="othersLabel">
                    <Translate contentKey="rosterServer3App.employee.help.others" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="employee-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.employee.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.employee.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="employee-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.employee.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.employee.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="employee-status">
                    <Translate contentKey="rosterServer3App.employee.status">Status</Translate>
                  </Label>
                  <AvInput id="employee-status" type="select" className="form-control" name="statusId">
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
                <AvGroup>
                  <Label for="employee-idType">
                    <Translate contentKey="rosterServer3App.employee.idType">Id Type</Translate>
                  </Label>
                  <AvInput id="employee-idType" type="select" className="form-control" name="idTypeId">
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
                <AvGroup>
                  <Label for="employee-contractType">
                    <Translate contentKey="rosterServer3App.employee.contractType">Contract Type</Translate>
                  </Label>
                  <AvInput id="employee-contractType" type="select" className="form-control" name="contractTypeId">
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
                  <Label for="employee-empType">
                    <Translate contentKey="rosterServer3App.employee.empType">Emp Type</Translate>
                  </Label>
                  <AvInput id="employee-empType" type="select" className="form-control" name="empTypeId">
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
                <AvGroup>
                  <Label for="employee-gender">
                    <Translate contentKey="rosterServer3App.employee.gender">Gender</Translate>
                  </Label>
                  <AvInput id="employee-gender" type="select" className="form-control" name="genderId">
                    <option value="" key="0" />
                    {enumGenders
                      ? enumGenders.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/employee" replace color="info">
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
  enumIdTypes: storeState.enumIdType.entities,
  enumContractTypes: storeState.enumContractType.entities,
  enumEmpTypes: storeState.enumEmpType.entities,
  enumGenders: storeState.enumGender.entities,
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating,
  updateSuccess: storeState.employee.updateSuccess
});

const mapDispatchToProps = {
  getEnumEmpStatuses,
  getEnumIdTypes,
  getEnumContractTypes,
  getEnumEmpTypes,
  getEnumGenders,
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
)(EmployeeUpdate);
