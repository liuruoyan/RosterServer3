import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './education-experience.reducer';
import { IEducationExperience } from 'app/shared/model/education-experience.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEducationExperienceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EducationExperienceDetail extends React.Component<IEducationExperienceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { educationExperienceEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.educationExperience.detail.title">EducationExperience</Translate> [
            <b>{educationExperienceEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.educationExperience.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.educationExperience.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.code}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="rosterServer3App.educationExperience.name">Name</Translate>
              </span>
              <UncontrolledTooltip target="name">
                <Translate contentKey="rosterServer3App.educationExperience.help.name" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.name}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="rosterServer3App.educationExperience.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="rosterServer3App.educationExperience.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.phone}</dd>
            <dt>
              <span id="school">
                <Translate contentKey="rosterServer3App.educationExperience.school">School</Translate>
              </span>
              <UncontrolledTooltip target="school">
                <Translate contentKey="rosterServer3App.educationExperience.help.school" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.school}</dd>
            <dt>
              <span id="major">
                <Translate contentKey="rosterServer3App.educationExperience.major">Major</Translate>
              </span>
              <UncontrolledTooltip target="major">
                <Translate contentKey="rosterServer3App.educationExperience.help.major" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.major}</dd>
            <dt>
              <span id="inDate">
                <Translate contentKey="rosterServer3App.educationExperience.inDate">In Date</Translate>
              </span>
              <UncontrolledTooltip target="inDate">
                <Translate contentKey="rosterServer3App.educationExperience.help.inDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={educationExperienceEntity.inDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="graduationDate">
                <Translate contentKey="rosterServer3App.educationExperience.graduationDate">Graduation Date</Translate>
              </span>
              <UncontrolledTooltip target="graduationDate">
                <Translate contentKey="rosterServer3App.educationExperience.help.graduationDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={educationExperienceEntity.graduationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="inception">
                <Translate contentKey="rosterServer3App.educationExperience.inception">Inception</Translate>
              </span>
              <UncontrolledTooltip target="inception">
                <Translate contentKey="rosterServer3App.educationExperience.help.inception" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.inception ? 'true' : 'false'}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.educationExperience.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.educationExperience.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.educationExperience.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.educationExperience.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{educationExperienceEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.educationExperience.education">Education</Translate>
            </dt>
            <dd>{educationExperienceEntity.educationId ? educationExperienceEntity.educationId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.educationExperience.emp">Emp</Translate>
            </dt>
            <dd>{educationExperienceEntity.empId ? educationExperienceEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/education-experience" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/education-experience/${educationExperienceEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ educationExperience }: IRootState) => ({
  educationExperienceEntity: educationExperience.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EducationExperienceDetail);
