import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EmployeeDetail extends React.Component<IEmployeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { employeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.employee.detail.title">Employee</Translate> [<b>{employeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.employee.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.employee.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.code}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="rosterServer3App.employee.name">Name</Translate>
              </span>
              <UncontrolledTooltip target="name">
                <Translate contentKey="rosterServer3App.employee.help.name" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.name}</dd>
            <dt>
              <span id="idNumber">
                <Translate contentKey="rosterServer3App.employee.idNumber">Id Number</Translate>
              </span>
              <UncontrolledTooltip target="idNumber">
                <Translate contentKey="rosterServer3App.employee.help.idNumber" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.idNumber}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="rosterServer3App.employee.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="rosterServer3App.employee.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.phone}</dd>
            <dt>
              <span id="hireDate">
                <Translate contentKey="rosterServer3App.employee.hireDate">Hire Date</Translate>
              </span>
              <UncontrolledTooltip target="hireDate">
                <Translate contentKey="rosterServer3App.employee.help.hireDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.hireDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="jobGrade">
                <Translate contentKey="rosterServer3App.employee.jobGrade">Job Grade</Translate>
              </span>
              <UncontrolledTooltip target="jobGrade">
                <Translate contentKey="rosterServer3App.employee.help.jobGrade" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.jobGrade}</dd>
            <dt>
              <span id="position">
                <Translate contentKey="rosterServer3App.employee.position">Position</Translate>
              </span>
              <UncontrolledTooltip target="position">
                <Translate contentKey="rosterServer3App.employee.help.position" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.position}</dd>
            <dt>
              <span id="job">
                <Translate contentKey="rosterServer3App.employee.job">Job</Translate>
              </span>
              <UncontrolledTooltip target="job">
                <Translate contentKey="rosterServer3App.employee.help.job" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.job}</dd>
            <dt>
              <span id="deptName">
                <Translate contentKey="rosterServer3App.employee.deptName">Dept Name</Translate>
              </span>
              <UncontrolledTooltip target="deptName">
                <Translate contentKey="rosterServer3App.employee.help.deptName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.deptName}</dd>
            <dt>
              <span id="empNo">
                <Translate contentKey="rosterServer3App.employee.empNo">Emp No</Translate>
              </span>
              <UncontrolledTooltip target="empNo">
                <Translate contentKey="rosterServer3App.employee.help.empNo" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.empNo}</dd>
            <dt>
              <span id="seniority">
                <Translate contentKey="rosterServer3App.employee.seniority">Seniority</Translate>
              </span>
              <UncontrolledTooltip target="seniority">
                <Translate contentKey="rosterServer3App.employee.help.seniority" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.seniority}</dd>
            <dt>
              <span id="contractor">
                <Translate contentKey="rosterServer3App.employee.contractor">Contractor</Translate>
              </span>
              <UncontrolledTooltip target="contractor">
                <Translate contentKey="rosterServer3App.employee.help.contractor" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.contractor}</dd>
            <dt>
              <span id="birthType">
                <Translate contentKey="rosterServer3App.employee.birthType">Birth Type</Translate>
              </span>
              <UncontrolledTooltip target="birthType">
                <Translate contentKey="rosterServer3App.employee.help.birthType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.birthType}</dd>
            <dt>
              <span id="birthday">
                <Translate contentKey="rosterServer3App.employee.birthday">Birthday</Translate>
              </span>
              <UncontrolledTooltip target="birthday">
                <Translate contentKey="rosterServer3App.employee.help.birthday" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.birthday} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="workLoc">
                <Translate contentKey="rosterServer3App.employee.workLoc">Work Loc</Translate>
              </span>
              <UncontrolledTooltip target="workLoc">
                <Translate contentKey="rosterServer3App.employee.help.workLoc" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.workLoc}</dd>
            <dt>
              <span id="contactAddr">
                <Translate contentKey="rosterServer3App.employee.contactAddr">Contact Addr</Translate>
              </span>
              <UncontrolledTooltip target="contactAddr">
                <Translate contentKey="rosterServer3App.employee.help.contactAddr" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.contactAddr}</dd>
            <dt>
              <span id="nationality">
                <Translate contentKey="rosterServer3App.employee.nationality">Nationality</Translate>
              </span>
              <UncontrolledTooltip target="nationality">
                <Translate contentKey="rosterServer3App.employee.help.nationality" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.nationality}</dd>
            <dt>
              <span id="firstName">
                <Translate contentKey="rosterServer3App.employee.firstName">First Name</Translate>
              </span>
              <UncontrolledTooltip target="firstName">
                <Translate contentKey="rosterServer3App.employee.help.firstName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.firstName}</dd>
            <dt>
              <span id="lastName">
                <Translate contentKey="rosterServer3App.employee.lastName">Last Name</Translate>
              </span>
              <UncontrolledTooltip target="lastName">
                <Translate contentKey="rosterServer3App.employee.help.lastName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.lastName}</dd>
            <dt>
              <span id="others">
                <Translate contentKey="rosterServer3App.employee.others">Others</Translate>
              </span>
              <UncontrolledTooltip target="others">
                <Translate contentKey="rosterServer3App.employee.help.others" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.others}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.employee.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.employee.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.employee.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.employee.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{employeeEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.employee.status">Status</Translate>
            </dt>
            <dd>{employeeEntity.statusId ? employeeEntity.statusId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.employee.idType">Id Type</Translate>
            </dt>
            <dd>{employeeEntity.idTypeId ? employeeEntity.idTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.employee.contractType">Contract Type</Translate>
            </dt>
            <dd>{employeeEntity.contractTypeId ? employeeEntity.contractTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.employee.empType">Emp Type</Translate>
            </dt>
            <dd>{employeeEntity.empTypeId ? employeeEntity.empTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.employee.gender">Gender</Translate>
            </dt>
            <dd>{employeeEntity.genderId ? employeeEntity.genderId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/employee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/employee/${employeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeEntity: employee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmployeeDetail);
