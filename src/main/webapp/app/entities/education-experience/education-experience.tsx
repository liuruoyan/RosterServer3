import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './education-experience.reducer';
import { IEducationExperience } from 'app/shared/model/education-experience.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IEducationExperienceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IEducationExperienceState = IPaginationBaseState;

export class EducationExperience extends React.Component<IEducationExperienceProps, IEducationExperienceState> {
  state: IEducationExperienceState = {
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
    const { educationExperienceList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="education-experience-heading">
          <Translate contentKey="rosterServer3App.educationExperience.home.title">Education Experiences</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.educationExperience.home.createLabel">Create new Education Experience</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {educationExperienceList && educationExperienceList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.educationExperience.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('name')}>
                    <Translate contentKey="rosterServer3App.educationExperience.name">Name</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phone')}>
                    <Translate contentKey="rosterServer3App.educationExperience.phone">Phone</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('school')}>
                    <Translate contentKey="rosterServer3App.educationExperience.school">School</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('major')}>
                    <Translate contentKey="rosterServer3App.educationExperience.major">Major</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('inDate')}>
                    <Translate contentKey="rosterServer3App.educationExperience.inDate">In Date</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('graduationDate')}>
                    <Translate contentKey="rosterServer3App.educationExperience.graduationDate">Graduation Date</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('inception')}>
                    <Translate contentKey="rosterServer3App.educationExperience.inception">Inception</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.educationExperience.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.educationExperience.isHrVerify">Is Hr Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.educationExperience.education">Education</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.educationExperience.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {educationExperienceList.map((educationExperience, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${educationExperience.id}`} color="link" size="sm">
                        {educationExperience.id}
                      </Button>
                    </td>
                    <td>{educationExperience.code}</td>
                    <td>{educationExperience.name}</td>
                    <td>{educationExperience.phone}</td>
                    <td>{educationExperience.school}</td>
                    <td>{educationExperience.major}</td>
                    <td>
                      <TextFormat type="date" value={educationExperience.inDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={educationExperience.graduationDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{educationExperience.inception ? 'true' : 'false'}</td>
                    <td>{educationExperience.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{educationExperience.isHrVerify ? 'true' : 'false'}</td>
                    <td>
                      {educationExperience.educationId ? (
                        <Link to={`enum-education/${educationExperience.educationId}`}>{educationExperience.educationId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {educationExperience.empId ? (
                        <Link to={`employee/${educationExperience.empId}`}>{educationExperience.empId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${educationExperience.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${educationExperience.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${educationExperience.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.educationExperience.home.notFound">No Education Experiences found</Translate>
            </div>
          )}
        </div>
        <div className={educationExperienceList && educationExperienceList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ educationExperience }: IRootState) => ({
  educationExperienceList: educationExperience.entities,
  totalItems: educationExperience.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EducationExperience);
