import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './social-security-benefits.reducer';
import { ISocialSecurityBenefits } from 'app/shared/model/social-security-benefits.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ISocialSecurityBenefitsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type ISocialSecurityBenefitsState = IPaginationBaseState;

export class SocialSecurityBenefits extends React.Component<ISocialSecurityBenefitsProps, ISocialSecurityBenefitsState> {
  state: ISocialSecurityBenefitsState = {
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
    const { socialSecurityBenefitsList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="social-security-benefits-heading">
          <Translate contentKey="rosterServer3App.socialSecurityBenefits.home.title">Social Security Benefits</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="rosterServer3App.socialSecurityBenefits.home.createLabel">Create new Social Security Benefits</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          {socialSecurityBenefitsList && socialSecurityBenefitsList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('code')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.code">Code</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pfAccount')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfAccount">Pf Account</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('spfAccount')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.spfAccount">Spf Account</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pfStartMonth')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStartMonth">Pf Start Month</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pfBase')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfBase">Pf Base</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pfStopMonth')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStopMonth">Pf Stop Month</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('pfRemark')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfRemark">Pf Remark</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssAccount')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssAccount">Ss Account</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssCity')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssCity">Ss City</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssStartMonth')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStartMonth">Ss Start Month</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssBase')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssBase">Ss Base</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssStopMonth')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStopMonth">Ss Stop Month</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('ssRemark')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssRemark">Ss Remark</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('allowance')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.allowance">Allowance</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('taxpayer')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxpayer">Taxpayer</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isSelfVerify')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.isSelfVerify">Is Self Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isHrVerify')}>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.isHrVerify">Is Hr Verify</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfType">Pf Type</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStatus">Pf Status</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.providentPayScheme">Provident Pay Scheme</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.socialSecurityPayScheme">
                      Social Security Pay Scheme
                    </Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStatus">Ss Status</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.laborType">Labor Type</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxerType">Taxer Type</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxArea">Tax Area</Translate>{' '}
                    <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.emp">Emp</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {socialSecurityBenefitsList.map((socialSecurityBenefits, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${socialSecurityBenefits.id}`} color="link" size="sm">
                        {socialSecurityBenefits.id}
                      </Button>
                    </td>
                    <td>{socialSecurityBenefits.code}</td>
                    <td>{socialSecurityBenefits.pfAccount}</td>
                    <td>{socialSecurityBenefits.spfAccount}</td>
                    <td>
                      <TextFormat type="date" value={socialSecurityBenefits.pfStartMonth} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{socialSecurityBenefits.pfBase}</td>
                    <td>
                      <TextFormat type="date" value={socialSecurityBenefits.pfStopMonth} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{socialSecurityBenefits.pfRemark}</td>
                    <td>{socialSecurityBenefits.ssAccount}</td>
                    <td>{socialSecurityBenefits.ssCity}</td>
                    <td>
                      <TextFormat type="date" value={socialSecurityBenefits.ssStartMonth} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{socialSecurityBenefits.ssBase}</td>
                    <td>
                      <TextFormat type="date" value={socialSecurityBenefits.ssStopMonth} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{socialSecurityBenefits.ssRemark}</td>
                    <td>{socialSecurityBenefits.allowance}</td>
                    <td>{socialSecurityBenefits.taxpayer}</td>
                    <td>{socialSecurityBenefits.isSelfVerify ? 'true' : 'false'}</td>
                    <td>{socialSecurityBenefits.isHrVerify ? 'true' : 'false'}</td>
                    <td>
                      {socialSecurityBenefits.pfTypeId ? (
                        <Link to={`enum-pf-type/${socialSecurityBenefits.pfTypeId}`}>{socialSecurityBenefits.pfTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.pfStatusId ? (
                        <Link to={`enum-pf-status/${socialSecurityBenefits.pfStatusId}`}>{socialSecurityBenefits.pfStatusId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.providentPaySchemeId ? (
                        <Link to={`enum-pf-pay-scheme/${socialSecurityBenefits.providentPaySchemeId}`}>
                          {socialSecurityBenefits.providentPaySchemeId}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.socialSecurityPaySchemeId ? (
                        <Link to={`enum-ss-pay-scheme/${socialSecurityBenefits.socialSecurityPaySchemeId}`}>
                          {socialSecurityBenefits.socialSecurityPaySchemeId}
                        </Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.ssStatusId ? (
                        <Link to={`enum-ss-status/${socialSecurityBenefits.ssStatusId}`}>{socialSecurityBenefits.ssStatusId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.laborTypeId ? (
                        <Link to={`enum-emp-labor-type/${socialSecurityBenefits.laborTypeId}`}>{socialSecurityBenefits.laborTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.taxerTypeId ? (
                        <Link to={`enum-emp-taxer-type/${socialSecurityBenefits.taxerTypeId}`}>{socialSecurityBenefits.taxerTypeId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.taxAreaId ? (
                        <Link to={`enum-emp-tax-area/${socialSecurityBenefits.taxAreaId}`}>{socialSecurityBenefits.taxAreaId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {socialSecurityBenefits.empId ? (
                        <Link to={`employee/${socialSecurityBenefits.empId}`}>{socialSecurityBenefits.empId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${socialSecurityBenefits.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${socialSecurityBenefits.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${socialSecurityBenefits.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.home.notFound">No Social Security Benefits found</Translate>
            </div>
          )}
        </div>
        <div className={socialSecurityBenefitsList && socialSecurityBenefitsList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ socialSecurityBenefits }: IRootState) => ({
  socialSecurityBenefitsList: socialSecurityBenefits.entities,
  totalItems: socialSecurityBenefits.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SocialSecurityBenefits);
