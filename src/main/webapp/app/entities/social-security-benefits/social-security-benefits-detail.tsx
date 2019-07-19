import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './social-security-benefits.reducer';
import { ISocialSecurityBenefits } from 'app/shared/model/social-security-benefits.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISocialSecurityBenefitsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SocialSecurityBenefitsDetail extends React.Component<ISocialSecurityBenefitsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { socialSecurityBenefitsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.socialSecurityBenefits.detail.title">SocialSecurityBenefits</Translate> [
            <b>{socialSecurityBenefitsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.code}</dd>
            <dt>
              <span id="pfAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfAccount">Pf Account</Translate>
              </span>
              <UncontrolledTooltip target="pfAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfAccount" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.pfAccount}</dd>
            <dt>
              <span id="spfAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.spfAccount">Spf Account</Translate>
              </span>
              <UncontrolledTooltip target="spfAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.spfAccount" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.spfAccount}</dd>
            <dt>
              <span id="pfStartMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStartMonth">Pf Start Month</Translate>
              </span>
              <UncontrolledTooltip target="pfStartMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfStartMonth" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={socialSecurityBenefitsEntity.pfStartMonth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="pfBase">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfBase">Pf Base</Translate>
              </span>
              <UncontrolledTooltip target="pfBase">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfBase" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.pfBase}</dd>
            <dt>
              <span id="pfStopMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStopMonth">Pf Stop Month</Translate>
              </span>
              <UncontrolledTooltip target="pfStopMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfStopMonth" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={socialSecurityBenefitsEntity.pfStopMonth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="pfRemark">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfRemark">Pf Remark</Translate>
              </span>
              <UncontrolledTooltip target="pfRemark">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfRemark" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.pfRemark}</dd>
            <dt>
              <span id="ssAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssAccount">Ss Account</Translate>
              </span>
              <UncontrolledTooltip target="ssAccount">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssAccount" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.ssAccount}</dd>
            <dt>
              <span id="ssCity">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssCity">Ss City</Translate>
              </span>
              <UncontrolledTooltip target="ssCity">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssCity" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.ssCity}</dd>
            <dt>
              <span id="ssStartMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStartMonth">Ss Start Month</Translate>
              </span>
              <UncontrolledTooltip target="ssStartMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssStartMonth" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={socialSecurityBenefitsEntity.ssStartMonth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ssBase">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssBase">Ss Base</Translate>
              </span>
              <UncontrolledTooltip target="ssBase">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssBase" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.ssBase}</dd>
            <dt>
              <span id="ssStopMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStopMonth">Ss Stop Month</Translate>
              </span>
              <UncontrolledTooltip target="ssStopMonth">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssStopMonth" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={socialSecurityBenefitsEntity.ssStopMonth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ssRemark">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssRemark">Ss Remark</Translate>
              </span>
              <UncontrolledTooltip target="ssRemark">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssRemark" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.ssRemark}</dd>
            <dt>
              <span id="allowance">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.allowance">Allowance</Translate>
              </span>
              <UncontrolledTooltip target="allowance">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.allowance" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.allowance}</dd>
            <dt>
              <span id="taxpayer">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxpayer">Taxpayer</Translate>
              </span>
              <UncontrolledTooltip target="taxpayer">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.taxpayer" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.taxpayer}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{socialSecurityBenefitsEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfType">Pf Type</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.pfTypeId ? socialSecurityBenefitsEntity.pfTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStatus">Pf Status</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.pfStatusId ? socialSecurityBenefitsEntity.pfStatusId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.providentPayScheme">Provident Pay Scheme</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.providentPaySchemeId ? socialSecurityBenefitsEntity.providentPaySchemeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.socialSecurityPayScheme">Social Security Pay Scheme</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.socialSecurityPaySchemeId ? socialSecurityBenefitsEntity.socialSecurityPaySchemeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStatus">Ss Status</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.ssStatusId ? socialSecurityBenefitsEntity.ssStatusId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.laborType">Labor Type</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.laborTypeId ? socialSecurityBenefitsEntity.laborTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxerType">Taxer Type</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.taxerTypeId ? socialSecurityBenefitsEntity.taxerTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxArea">Tax Area</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.taxAreaId ? socialSecurityBenefitsEntity.taxAreaId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.emp">Emp</Translate>
            </dt>
            <dd>{socialSecurityBenefitsEntity.empId ? socialSecurityBenefitsEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/social-security-benefits" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/social-security-benefits/${socialSecurityBenefitsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ socialSecurityBenefits }: IRootState) => ({
  socialSecurityBenefitsEntity: socialSecurityBenefits.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SocialSecurityBenefitsDetail);
