import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './personal.reducer';
import { IPersonal } from 'app/shared/model/personal.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPersonalDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PersonalDetail extends React.Component<IPersonalDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { personalEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.personal.detail.title">Personal</Translate> [<b>{personalEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.personal.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.personal.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.code}</dd>
            <dt>
              <span id="stageName">
                <Translate contentKey="rosterServer3App.personal.stageName">Stage Name</Translate>
              </span>
              <UncontrolledTooltip target="stageName">
                <Translate contentKey="rosterServer3App.personal.help.stageName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.stageName}</dd>
            <dt>
              <span id="idName">
                <Translate contentKey="rosterServer3App.personal.idName">Id Name</Translate>
              </span>
              <UncontrolledTooltip target="idName">
                <Translate contentKey="rosterServer3App.personal.help.idName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.idName}</dd>
            <dt>
              <span id="nation">
                <Translate contentKey="rosterServer3App.personal.nation">Nation</Translate>
              </span>
              <UncontrolledTooltip target="nation">
                <Translate contentKey="rosterServer3App.personal.help.nation" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.nation}</dd>
            <dt>
              <span id="accountLoc">
                <Translate contentKey="rosterServer3App.personal.accountLoc">Account Loc</Translate>
              </span>
              <UncontrolledTooltip target="accountLoc">
                <Translate contentKey="rosterServer3App.personal.help.accountLoc" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.accountLoc}</dd>
            <dt>
              <span id="nativePlace">
                <Translate contentKey="rosterServer3App.personal.nativePlace">Native Place</Translate>
              </span>
              <UncontrolledTooltip target="nativePlace">
                <Translate contentKey="rosterServer3App.personal.help.nativePlace" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.nativePlace}</dd>
            <dt>
              <span id="currentAddr">
                <Translate contentKey="rosterServer3App.personal.currentAddr">Current Addr</Translate>
              </span>
              <UncontrolledTooltip target="currentAddr">
                <Translate contentKey="rosterServer3App.personal.help.currentAddr" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.currentAddr}</dd>
            <dt>
              <span id="spouseName">
                <Translate contentKey="rosterServer3App.personal.spouseName">Spouse Name</Translate>
              </span>
              <UncontrolledTooltip target="spouseName">
                <Translate contentKey="rosterServer3App.personal.help.spouseName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.spouseName}</dd>
            <dt>
              <span id="childName">
                <Translate contentKey="rosterServer3App.personal.childName">Child Name</Translate>
              </span>
              <UncontrolledTooltip target="childName">
                <Translate contentKey="rosterServer3App.personal.help.childName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.childName}</dd>
            <dt>
              <span id="bloodType">
                <Translate contentKey="rosterServer3App.personal.bloodType">Blood Type</Translate>
              </span>
              <UncontrolledTooltip target="bloodType">
                <Translate contentKey="rosterServer3App.personal.help.bloodType" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.bloodType}</dd>
            <dt>
              <span id="emergencyContactName">
                <Translate contentKey="rosterServer3App.personal.emergencyContactName">Emergency Contact Name</Translate>
              </span>
              <UncontrolledTooltip target="emergencyContactName">
                <Translate contentKey="rosterServer3App.personal.help.emergencyContactName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.emergencyContactName}</dd>
            <dt>
              <span id="emergencyContactPhone">
                <Translate contentKey="rosterServer3App.personal.emergencyContactPhone">Emergency Contact Phone</Translate>
              </span>
              <UncontrolledTooltip target="emergencyContactPhone">
                <Translate contentKey="rosterServer3App.personal.help.emergencyContactPhone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.emergencyContactPhone}</dd>
            <dt>
              <span id="qq">
                <Translate contentKey="rosterServer3App.personal.qq">Qq</Translate>
              </span>
              <UncontrolledTooltip target="qq">
                <Translate contentKey="rosterServer3App.personal.help.qq" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.qq}</dd>
            <dt>
              <span id="wechat">
                <Translate contentKey="rosterServer3App.personal.wechat">Wechat</Translate>
              </span>
              <UncontrolledTooltip target="wechat">
                <Translate contentKey="rosterServer3App.personal.help.wechat" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.wechat}</dd>
            <dt>
              <span id="personalEmail">
                <Translate contentKey="rosterServer3App.personal.personalEmail">Personal Email</Translate>
              </span>
              <UncontrolledTooltip target="personalEmail">
                <Translate contentKey="rosterServer3App.personal.help.personalEmail" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.personalEmail}</dd>
            <dt>
              <span id="remark">
                <Translate contentKey="rosterServer3App.personal.remark">Remark</Translate>
              </span>
              <UncontrolledTooltip target="remark">
                <Translate contentKey="rosterServer3App.personal.help.remark" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.remark}</dd>
            <dt>
              <span id="others">
                <Translate contentKey="rosterServer3App.personal.others">Others</Translate>
              </span>
              <UncontrolledTooltip target="others">
                <Translate contentKey="rosterServer3App.personal.help.others" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.others}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.personal.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.personal.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.personal.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.personal.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{personalEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.personal.accountType">Account Type</Translate>
            </dt>
            <dd>{personalEntity.accountTypeId ? personalEntity.accountTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.personal.highestEducation">Highest Education</Translate>
            </dt>
            <dd>{personalEntity.highestEducationId ? personalEntity.highestEducationId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.personal.politicsStatus">Politics Status</Translate>
            </dt>
            <dd>{personalEntity.politicsStatusId ? personalEntity.politicsStatusId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.personal.maritalStatus">Marital Status</Translate>
            </dt>
            <dd>{personalEntity.maritalStatusId ? personalEntity.maritalStatusId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.personal.emp">Emp</Translate>
            </dt>
            <dd>{personalEntity.empId ? personalEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/personal" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/personal/${personalEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ personal }: IRootState) => ({
  personalEntity: personal.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PersonalDetail);
