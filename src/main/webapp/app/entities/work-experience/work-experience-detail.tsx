import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './work-experience.reducer';
import { IWorkExperience } from 'app/shared/model/work-experience.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IWorkExperienceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class WorkExperienceDetail extends React.Component<IWorkExperienceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { workExperienceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.workExperience.detail.title">WorkExperience</Translate> [
            <b>{workExperienceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.workExperience.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.workExperience.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.code}</dd>
            <dt>
              <span id="eName">
                <Translate contentKey="rosterServer3App.workExperience.eName">E Name</Translate>
              </span>
              <UncontrolledTooltip target="eName">
                <Translate contentKey="rosterServer3App.workExperience.help.eName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.eName}</dd>
            <dt>
              <span id="phoneNum">
                <Translate contentKey="rosterServer3App.workExperience.phoneNum">Phone Num</Translate>
              </span>
              <UncontrolledTooltip target="phoneNum">
                <Translate contentKey="rosterServer3App.workExperience.help.phoneNum" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.phoneNum}</dd>
            <dt>
              <span id="company">
                <Translate contentKey="rosterServer3App.workExperience.company">Company</Translate>
              </span>
              <UncontrolledTooltip target="company">
                <Translate contentKey="rosterServer3App.workExperience.help.company" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.company}</dd>
            <dt>
              <span id="job">
                <Translate contentKey="rosterServer3App.workExperience.job">Job</Translate>
              </span>
              <UncontrolledTooltip target="job">
                <Translate contentKey="rosterServer3App.workExperience.help.job" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.job}</dd>
            <dt>
              <span id="jobDesc">
                <Translate contentKey="rosterServer3App.workExperience.jobDesc">Job Desc</Translate>
              </span>
              <UncontrolledTooltip target="jobDesc">
                <Translate contentKey="rosterServer3App.workExperience.help.jobDesc" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.jobDesc}</dd>
            <dt>
              <span id="hireDate">
                <Translate contentKey="rosterServer3App.workExperience.hireDate">Hire Date</Translate>
              </span>
              <UncontrolledTooltip target="hireDate">
                <Translate contentKey="rosterServer3App.workExperience.help.hireDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={workExperienceEntity.hireDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="leaveDate">
                <Translate contentKey="rosterServer3App.workExperience.leaveDate">Leave Date</Translate>
              </span>
              <UncontrolledTooltip target="leaveDate">
                <Translate contentKey="rosterServer3App.workExperience.help.leaveDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={workExperienceEntity.leaveDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.workExperience.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.workExperience.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.workExperience.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.workExperience.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{workExperienceEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.workExperience.emp">Emp</Translate>
            </dt>
            <dd>{workExperienceEntity.empId ? workExperienceEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/work-experience" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/work-experience/${workExperienceEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ workExperience }: IRootState) => ({
  workExperienceEntity: workExperience.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(WorkExperienceDetail);
