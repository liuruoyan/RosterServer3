import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './additional-post.reducer';
import { IAdditionalPost } from 'app/shared/model/additional-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IAdditionalPostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IAdditionalPostState = IPaginationBaseState;

export class AdditionalPost extends React.Component<IAdditionalPostProps, IAdditionalPostState> {
  state: IAdditionalPostState = {
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
    const { additionalPostList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="additional-post-heading">
          <Translate contentKey="rosterServer3App.additionalPost.home.title">Additional Posts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.additionalPost.home.createLabel">Create new Additional Post</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {additionalPostList && additionalPostList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.additionalPost.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('name')}>
                    <Translate contentKey="rosterServer3App.additionalPost.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="rosterServer3App.additionalPost.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('dept')}>
                    <Translate contentKey="rosterServer3App.additionalPost.dept">Dept</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('job')}>
                    <Translate contentKey="rosterServer3App.additionalPost.job">Job</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('startDate')}>
                    <Translate contentKey="rosterServer3App.additionalPost.startDate">Start Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('endDate')}>
                    <Translate contentKey="rosterServer3App.additionalPost.endDate">End Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('remark')}>
                    <Translate contentKey="rosterServer3App.additionalPost.remark">Remark</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.additionalPost.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.additionalPost.isHrVerify">Is Hr Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.additionalPost.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {additionalPostList.map((additionalPost, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${additionalPost.id}`} color="link" size="sm">
                        {additionalPost.id}
                      </Button>
                    </td>
                    <td>{additionalPost.code}</td>
                    <td>{additionalPost.name}</td>
                    <td>{additionalPost.phone}</td>
                    <td>{additionalPost.dept}</td>
                    <td>{additionalPost.job}</td>
                    <td>
                      <TextFormat type="date" value={additionalPost.startDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={additionalPost.endDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{additionalPost.remark}</td>
                    <td>{additionalPost.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{additionalPost.isHrVerify ? 'true' : 'false'}</td>
                    <td>{additionalPost.empId ? <Link to={`employee/${additionalPost.empId}`}>{additionalPost.empId}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${additionalPost.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${additionalPost.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${additionalPost.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.additionalPost.home.notFound">No Additional Posts found</Translate>
            </div>
          )}
        </div>
        <div className={additionalPostList && additionalPostList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ additionalPost }: IRootState) => ({
  additionalPostList: additionalPost.entities,
  totalItems: additionalPost.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdditionalPost);
