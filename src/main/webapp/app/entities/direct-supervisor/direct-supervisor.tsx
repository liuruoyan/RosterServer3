import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './direct-supervisor.reducer';
import { IDirectSupervisor } from 'app/shared/model/direct-supervisor.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IDirectSupervisorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IDirectSupervisorState = IPaginationBaseState;

export class DirectSupervisor extends React.Component<IDirectSupervisorProps, IDirectSupervisorState> {
  state: IDirectSupervisorState = {
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
    const { directSupervisorList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="direct-supervisor-heading">
          <Translate contentKey="rosterServer3App.directSupervisor.home.title">Direct Supervisors</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.directSupervisor.home.createLabel">Create new Direct Supervisor</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {directSupervisorList && directSupervisorList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('name')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('aSupName')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.aSupName">A Sup Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('aSupPhone')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.aSupPhone">A Sup Phone</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bSupName')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.bSupName">B Sup Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('bSupPhone')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.bSupPhone">B Sup Phone</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('fSubName')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.fSubName">F Sub Name</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('fSubPhone')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.fSubPhone">F Sub Phone</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.directSupervisor.isHrVerify">Is Hr Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.directSupervisor.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {directSupervisorList.map((directSupervisor, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${directSupervisor.id}`} color="link" size="sm">
                        {directSupervisor.id}
                      </Button>
                    </td>
                    <td>{directSupervisor.code}</td>
                    <td>{directSupervisor.name}</td>
                    <td>{directSupervisor.phone}</td>
                    <td>{directSupervisor.aSupName}</td>
                    <td>{directSupervisor.aSupPhone}</td>
                    <td>{directSupervisor.bSupName}</td>
                    <td>{directSupervisor.bSupPhone}</td>
                    <td>{directSupervisor.fSubName}</td>
                    <td>{directSupervisor.fSubPhone}</td>
                    <td>{directSupervisor.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{directSupervisor.isHrVerify ? 'true' : 'false'}</td>
                    <td>{directSupervisor.empId ? <Link to={`employee/${directSupervisor.empId}`}>{directSupervisor.empId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${directSupervisor.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${directSupervisor.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${directSupervisor.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.directSupervisor.home.notFound">No Direct Supervisors found</Translate>
            </div>
          )}
        </div>
        <div className={directSupervisorList && directSupervisorList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ directSupervisor }: IRootState) => ({
  directSupervisorList: directSupervisor.entities,
  totalItems: directSupervisor.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DirectSupervisor);
