import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IEmployeeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IEmployeeState = IPaginationBaseState;

export class Employee extends React.Component<IEmployeeProps, IEmployeeState> {
  state: IEmployeeState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { employeeList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="employee-heading">
          <Translate contentKey="rosterServer3App.employee.home.title">Employees</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.employee.home.createLabel">Create new Employee</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {employeeList && employeeList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.employee.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('name')}>
                    <Translate contentKey="rosterServer3App.employee.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('idNumber')}>
                    <Translate contentKey="rosterServer3App.employee.idNumber">Id Number</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="rosterServer3App.employee.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('hireDate')}>
                    <Translate contentKey="rosterServer3App.employee.hireDate">Hire Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('jobGrade')}>
                    <Translate contentKey="rosterServer3App.employee.jobGrade">Job Grade</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('position')}>
                    <Translate contentKey="rosterServer3App.employee.position">Position</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('job')}>
                    <Translate contentKey="rosterServer3App.employee.job">Job</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('deptName')}>
                    <Translate contentKey="rosterServer3App.employee.deptName">Dept Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('empNo')}>
                    <Translate contentKey="rosterServer3App.employee.empNo">Emp No</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('seniority')}>
                    <Translate contentKey="rosterServer3App.employee.seniority">Seniority</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('contractor')}>
                    <Translate contentKey="rosterServer3App.employee.contractor">Contractor</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('birthType')}>
                    <Translate contentKey="rosterServer3App.employee.birthType">Birth Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('birthday')}>
                    <Translate contentKey="rosterServer3App.employee.birthday">Birthday</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('workLoc')}>
                    <Translate contentKey="rosterServer3App.employee.workLoc">Work Loc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('contactAddr')}>
                    <Translate contentKey="rosterServer3App.employee.contactAddr">Contact Addr</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('nationality')}>
                    <Translate contentKey="rosterServer3App.employee.nationality">Nationality</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('firstName')}>
                    <Translate contentKey="rosterServer3App.employee.firstName">First Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('lastName')}>
                    <Translate contentKey="rosterServer3App.employee.lastName">Last Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('others')}>
                    <Translate contentKey="rosterServer3App.employee.others">Others</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.employee.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.employee.isHrVerify">Is Hr Verify</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.employee.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.employee.idType">Id Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.employee.contractType">Contract Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.employee.empType">Emp Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.employee.gender">Gender</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {employeeList.map((employee, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${employee.id}`} color="link" size="sm">
                        {employee.id}
                      </Button>
                    </td>
                    <td>{employee.code}</td>
                    <td>{employee.name}</td>
                    <td>{employee.idNumber}</td>
                    <td>{employee.phone}</td>
                    <td>
                      <TextFormat type="date" value={employee.hireDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{employee.jobGrade}</td>
                    <td>{employee.position}</td>
                    <td>{employee.job}</td>
                    <td>{employee.deptName}</td>
                    <td>{employee.empNo}</td>
                    <td>{employee.seniority}</td>
                    <td>{employee.contractor}</td>
                    <td>
                      <Translate contentKey={`rosterServer3App.BirthType.${employee.birthType}`} />
                    </td>
                    <td>
                      <TextFormat type="date" value={employee.birthday} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{employee.workLoc}</td>
                    <td>{employee.contactAddr}</td>
                    <td>{employee.nationality}</td>
                    <td>{employee.firstName}</td>
                    <td>{employee.lastName}</td>
                    <td>{employee.others}</td>
                    <td>{employee.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{employee.isHrVerify ? 'true' : 'false'}</td>
                    <td>{employee.statusId ? <Link to={`enum-emp-status/${employee.statusId}`}>{employee.statusId}</Link> : ''}</td>
                    <td>{employee.idTypeId ? <Link to={`enum-id-type/${employee.idTypeId}`}>{employee.idTypeId}</Link> : ''}</td>
                    <td>
                      {employee.contractTypeId ? (
                        <Link to={`enum-contract-type/${employee.contractTypeId}`}>{employee.contractTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>{employee.empTypeId ? <Link to={`enum-emp-type/${employee.empTypeId}`}>{employee.empTypeId}</Link> : ''}</td>
                    <td>{employee.genderId ? <Link to={`enum-gender/${employee.genderId}`}>{employee.genderId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${employee.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${employee.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${employee.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">
              <Translate contentKey="rosterServer3App.employee.home.notFound">No Employees found</Translate>
            </div>
          )}
        </div>
        <div className={employeeList && employeeList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={this.state.activePage} total={totalItems} itemsPerPage={this.state.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={this.state.activePage}
              onSelect={this.handlePagination}
              maxButtons={5}
              itemsPerPage={this.state.itemsPerPage}
              totalItems={this.props.totalItems}
            />
          </Row>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeList: employee.entities,
  totalItems: employee.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Employee);
