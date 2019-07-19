import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContractDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ContractDetail extends React.Component<IContractDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { contractEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.contract.detail.title">Contract</Translate> [<b>{contractEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.contract.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.contract.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.code}</dd>
            <dt>
              <span id="startDate">
                <Translate contentKey="rosterServer3App.contract.startDate">Start Date</Translate>
              </span>
              <UncontrolledTooltip target="startDate">
                <Translate contentKey="rosterServer3App.contract.help.startDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={contractEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">
                <Translate contentKey="rosterServer3App.contract.endDate">End Date</Translate>
              </span>
              <UncontrolledTooltip target="endDate">
                <Translate contentKey="rosterServer3App.contract.help.endDate" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={contractEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="email">
                <Translate contentKey="rosterServer3App.contract.email">Email</Translate>
              </span>
              <UncontrolledTooltip target="email">
                <Translate contentKey="rosterServer3App.contract.help.email" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.email}</dd>
            <dt>
              <span id="workTel">
                <Translate contentKey="rosterServer3App.contract.workTel">Work Tel</Translate>
              </span>
              <UncontrolledTooltip target="workTel">
                <Translate contentKey="rosterServer3App.contract.help.workTel" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.workTel}</dd>
            <dt>
              <span id="probationEndDay">
                <Translate contentKey="rosterServer3App.contract.probationEndDay">Probation End Day</Translate>
              </span>
              <UncontrolledTooltip target="probationEndDay">
                <Translate contentKey="rosterServer3App.contract.help.probationEndDay" />
              </UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={contractEntity.probationEndDay} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="probationLength">
                <Translate contentKey="rosterServer3App.contract.probationLength">Probation Length</Translate>
              </span>
              <UncontrolledTooltip target="probationLength">
                <Translate contentKey="rosterServer3App.contract.help.probationLength" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.probationLength}</dd>
            <dt>
              <span id="other">
                <Translate contentKey="rosterServer3App.contract.other">Other</Translate>
              </span>
              <UncontrolledTooltip target="other">
                <Translate contentKey="rosterServer3App.contract.help.other" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.other}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.contract.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.contract.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.contract.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.contract.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{contractEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.contract.contractType">Contract Type</Translate>
            </dt>
            <dd>{contractEntity.contractTypeId ? contractEntity.contractTypeId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.contract.emp">Emp</Translate>
            </dt>
            <dd>{contractEntity.empId ? contractEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/contract" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/contract/${contractEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ contract }: IRootState) => ({
  contractEntity: contract.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ContractDetail);
