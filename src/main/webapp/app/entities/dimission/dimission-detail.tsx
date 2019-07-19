import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dimission.reducer';
import { IDimission } from 'app/shared/model/dimission.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDimissionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DimissionDetail extends React.Component<IDimissionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { dimissionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.dimission.detail.title">Dimission</Translate> [<b>{dimissionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.dimission.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.dimission.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{dimissionEntity.code}</dd>
            <dt>
              <span id="lastDate">
                <Translate contentKey="rosterServer3App.dimission.lastDate">Last Date</Translate>
              </span>
              <UncontrolledTooltip target="lastDate">
                <Translate contentKey="rosterServer3App.dimission.help.lastDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={dimissionEntity.lastDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="reason">
                <Translate contentKey="rosterServer3App.dimission.reason">Reason</Translate>
              </span>
              <UncontrolledTooltip target="reason">
                <Translate contentKey="rosterServer3App.dimission.help.reason" />
              </UncontrolledTooltip>
            </dt>
            <dd>{dimissionEntity.reason}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.dimission.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.dimission.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{dimissionEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.dimission.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.dimission.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{dimissionEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.dimission.dimissionType">Dimission Type</Translate>
            </dt>
            <dd>{dimissionEntity.dimissionTypeId ? dimissionEntity.dimissionTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.dimission.emp">Emp</Translate>
            </dt>
            <dd>{dimissionEntity.empId ? dimissionEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/dimission" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/dimission/${dimissionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ dimission }: IRootState) => ({
  dimissionEntity: dimission.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DimissionDetail);
