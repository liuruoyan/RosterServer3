import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './additional-post.reducer';
import { IAdditionalPost } from 'app/shared/model/additional-post.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdditionalPostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdditionalPostDetail extends React.Component<IAdditionalPostDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { additionalPostEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.additionalPost.detail.title">AdditionalPost</Translate> [
            <b>{additionalPostEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.additionalPost.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.additionalPost.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.code}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="rosterServer3App.additionalPost.name">Name</Translate>
              </span>
              <UncontrolledTooltip target="name">
                <Translate contentKey="rosterServer3App.additionalPost.help.name" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.name}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="rosterServer3App.additionalPost.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="rosterServer3App.additionalPost.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.phone}</dd>
            <dt>
              <span id="dept">
                <Translate contentKey="rosterServer3App.additionalPost.dept">Dept</Translate>
              </span>
              <UncontrolledTooltip target="dept">
                <Translate contentKey="rosterServer3App.additionalPost.help.dept" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.dept}</dd>
            <dt>
              <span id="job">
                <Translate contentKey="rosterServer3App.additionalPost.job">Job</Translate>
              </span>
              <UncontrolledTooltip target="job">
                <Translate contentKey="rosterServer3App.additionalPost.help.job" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.job}</dd>
            <dt>
              <span id="startDate">
                <Translate contentKey="rosterServer3App.additionalPost.startDate">Start Date</Translate>
              </span>
              <UncontrolledTooltip target="startDate">
                <Translate contentKey="rosterServer3App.additionalPost.help.startDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={additionalPostEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">
                <Translate contentKey="rosterServer3App.additionalPost.endDate">End Date</Translate>
              </span>
              <UncontrolledTooltip target="endDate">
                <Translate contentKey="rosterServer3App.additionalPost.help.endDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={additionalPostEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="remark">
                <Translate contentKey="rosterServer3App.additionalPost.remark">Remark</Translate>
              </span>
              <UncontrolledTooltip target="remark">
                <Translate contentKey="rosterServer3App.additionalPost.help.remark" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.remark}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.additionalPost.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.additionalPost.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.additionalPost.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.additionalPost.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{additionalPostEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.additionalPost.emp">Emp</Translate>
            </dt>
            <dd>{additionalPostEntity.empId ? additionalPostEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/additional-post" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/additional-post/${additionalPostEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ additionalPost }: IRootState) => ({
  additionalPostEntity: additionalPost.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdditionalPostDetail);
