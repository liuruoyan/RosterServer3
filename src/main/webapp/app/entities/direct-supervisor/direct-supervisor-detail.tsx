import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './direct-supervisor.reducer';
import { IDirectSupervisor } from 'app/shared/model/direct-supervisor.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDirectSupervisorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DirectSupervisorDetail extends React.Component<IDirectSupervisorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { directSupervisorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.directSupervisor.detail.title">DirectSupervisor</Translate> [
            <b>{directSupervisorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.directSupervisor.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.directSupervisor.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.code}</dd>
            <dt>
              <span id="name">
                <Translate contentKey="rosterServer3App.directSupervisor.name">Name</Translate>
              </span>
              <UncontrolledTooltip target="name">
                <Translate contentKey="rosterServer3App.directSupervisor.help.name" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.name}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="rosterServer3App.directSupervisor.phone">Phone</Translate>
              </span>
              <UncontrolledTooltip target="phone">
                <Translate contentKey="rosterServer3App.directSupervisor.help.phone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.phone}</dd>
            <dt>
              <span id="aSupName">
                <Translate contentKey="rosterServer3App.directSupervisor.aSupName">A Sup Name</Translate>
              </span>
              <UncontrolledTooltip target="aSupName">
                <Translate contentKey="rosterServer3App.directSupervisor.help.aSupName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.aSupName}</dd>
            <dt>
              <span id="aSupPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.aSupPhone">A Sup Phone</Translate>
              </span>
              <UncontrolledTooltip target="aSupPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.help.aSupPhone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.aSupPhone}</dd>
            <dt>
              <span id="bSupName">
                <Translate contentKey="rosterServer3App.directSupervisor.bSupName">B Sup Name</Translate>
              </span>
              <UncontrolledTooltip target="bSupName">
                <Translate contentKey="rosterServer3App.directSupervisor.help.bSupName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.bSupName}</dd>
            <dt>
              <span id="bSupPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.bSupPhone">B Sup Phone</Translate>
              </span>
              <UncontrolledTooltip target="bSupPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.help.bSupPhone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.bSupPhone}</dd>
            <dt>
              <span id="fSubName">
                <Translate contentKey="rosterServer3App.directSupervisor.fSubName">F Sub Name</Translate>
              </span>
              <UncontrolledTooltip target="fSubName">
                <Translate contentKey="rosterServer3App.directSupervisor.help.fSubName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.fSubName}</dd>
            <dt>
              <span id="fSubPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.fSubPhone">F Sub Phone</Translate>
              </span>
              <UncontrolledTooltip target="fSubPhone">
                <Translate contentKey="rosterServer3App.directSupervisor.help.fSubPhone" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.fSubPhone}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.directSupervisor.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.directSupervisor.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.directSupervisor.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.directSupervisor.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{directSupervisorEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.directSupervisor.emp">Emp</Translate>
            </dt>
            <dd>{directSupervisorEntity.empId ? directSupervisorEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/direct-supervisor" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/direct-supervisor/${directSupervisorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ directSupervisor }: IRootState) => ({
  directSupervisorEntity: directSupervisor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DirectSupervisorDetail);
