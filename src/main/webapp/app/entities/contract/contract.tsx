import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IContractProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IContractState = IPaginationBaseState;

export class Contract extends React.Component<IContractProps, IContractState> {
  state: IContractState = {
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
    const { contractList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="contract-heading">
          <Translate contentKey="rosterServer3App.contract.home.title">Contracts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.contract.home.createLabel">Create new Contract</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {contractList && contractList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.contract.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('startDate')}>
                    <Translate contentKey="rosterServer3App.contract.startDate">Start Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('endDate')}>
                    <Translate contentKey="rosterServer3App.contract.endDate">End Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('email')}>
                    <Translate contentKey="rosterServer3App.contract.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('workTel')}>
                    <Translate contentKey="rosterServer3App.contract.workTel">Work Tel</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('probationEndDay')}>
                    <Translate contentKey="rosterServer3App.contract.probationEndDay">Probation End Day</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('probationLength')}>
                    <Translate contentKey="rosterServer3App.contract.probationLength">Probation Length</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('other')}>
                    <Translate contentKey="rosterServer3App.contract.other">Other</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.contract.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.contract.isHrVerify">Is Hr Verify</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.contract.contractType">Contract Type</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.contract.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {contractList.map((contract, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${contract.id}`} color="link" size="sm">
                        {contract.id}
                      </Button>
                    </td>
                    <td>{contract.code}</td>
                    <td>
                      <TextFormat type="date" value={contract.startDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={contract.endDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{contract.email}</td>
                    <td>{contract.workTel}</td>
                    <td>
                      <TextFormat type="date" value={contract.probationEndDay} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{contract.probationLength}</td>
                    <td>{contract.other}</td>
                    <td>{contract.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{contract.isHrVerify ? 'true' : 'false'}</td>
                    <td>
                      {contract.contractTypeId ? (
                        <Link to={`enum-contract-type/${contract.contractTypeId}`}>{contract.contractTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>{contract.empId ? <Link to={`employee/${contract.empId}`}>{contract.empId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${contract.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${contract.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${contract.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.contract.home.notFound">No Contracts found</Translate>
            </div>
          )}
        </div>
        <div className={contractList && contractList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ contract }: IRootState) => ({
  contractList: contract.entities,
  totalItems: contract.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Contract);
