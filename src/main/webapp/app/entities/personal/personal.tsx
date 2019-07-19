import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './personal.reducer';
import { IPersonal } from 'app/shared/model/personal.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IPersonalProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IPersonalState = IPaginationBaseState;

export class Personal extends React.Component<IPersonalProps, IPersonalState> {
  state: IPersonalState = {
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
    const { personalList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="personal-heading">
          <Translate contentKey="rosterServer3App.personal.home.title">Personals</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.personal.home.createLabel">Create new Personal</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {personalList && personalList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.personal.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('stageName')}>
                    <Translate contentKey="rosterServer3App.personal.stageName">Stage Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('idName')}>
                    <Translate contentKey="rosterServer3App.personal.idName">Id Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('nation')}>
                    <Translate contentKey="rosterServer3App.personal.nation">Nation</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('accountLoc')}>
                    <Translate contentKey="rosterServer3App.personal.accountLoc">Account Loc</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('nativePlace')}>
                    <Translate contentKey="rosterServer3App.personal.nativePlace">Native Place</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('currentAddr')}>
                    <Translate contentKey="rosterServer3App.personal.currentAddr">Current Addr</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('spouseName')}>
                    <Translate contentKey="rosterServer3App.personal.spouseName">Spouse Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('childName')}>
                    <Translate contentKey="rosterServer3App.personal.childName">Child Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bloodType')}>
                    <Translate contentKey="rosterServer3App.personal.bloodType">Blood Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('emergencyContactName')}>
                    <Translate contentKey="rosterServer3App.personal.emergencyContactName">Emergency Contact Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('emergencyContactPhone')}>
                    <Translate contentKey="rosterServer3App.personal.emergencyContactPhone">Emergency Contact Phone</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('qq')}>
                    <Translate contentKey="rosterServer3App.personal.qq">Qq</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('wechat')}>
                    <Translate contentKey="rosterServer3App.personal.wechat">Wechat</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('personalEmail')}>
                    <Translate contentKey="rosterServer3App.personal.personalEmail">Personal Email</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remark')}>
                    <Translate contentKey="rosterServer3App.personal.remark">Remark</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('others')}>
                    <Translate contentKey="rosterServer3App.personal.others">Others</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.personal.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.personal.isHrVerify">Is Hr Verify</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.personal.accountType">Account Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.personal.highestEducation">Highest Education</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.personal.politicsStatus">Politics Status</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.personal.maritalStatus">Marital Status</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.personal.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {personalList.map((personal, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${personal.id}`} color="link" size="sm">
                        {personal.id}
                      </Button>
                    </td>
                    <td>{personal.code}</td>
                    <td>{personal.stageName}</td>
                    <td>{personal.idName}</td>
                    <td>{personal.nation}</td>
                    <td>{personal.accountLoc}</td>
                    <td>{personal.nativePlace}</td>
                    <td>{personal.currentAddr}</td>
                    <td>{personal.spouseName}</td>
                    <td>{personal.childName}</td>
                    <td>
                      <Translate contentKey={`rosterServer3App.BloodType.${personal.bloodType}`} />
                    </td>
                    <td>{personal.emergencyContactName}</td>
                    <td>{personal.emergencyContactPhone}</td>
                    <td>{personal.qq}</td>
                    <td>{personal.wechat}</td>
                    <td>{personal.personalEmail}</td>
                    <td>{personal.remark}</td>
                    <td>{personal.others}</td>
                    <td>{personal.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{personal.isHrVerify ? 'true' : 'false'}</td>
                    <td>
                      {personal.accountTypeId ? (
                        <Link to={`enum-account-type/${personal.accountTypeId}`}>{personal.accountTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {personal.highestEducationId ? (
                        <Link to={`enum-highest-education/${personal.highestEducationId}`}>{personal.highestEducationId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {personal.politicsStatusId ? (
                        <Link to={`enum-politics-status/${personal.politicsStatusId}`}>{personal.politicsStatusId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {personal.maritalStatusId ? (
                        <Link to={`enum-marital-status/${personal.maritalStatusId}`}>{personal.maritalStatusId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>{personal.empId ? <Link to={`employee/${personal.empId}`}>{personal.empId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${personal.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${personal.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${personal.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.personal.home.notFound">No Personals found</Translate>
            </div>
          )}
        </div>
        <div className={personalList && personalList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ personal }: IRootState) => ({
  personalList: personal.entities,
  totalItems: personal.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Personal);
