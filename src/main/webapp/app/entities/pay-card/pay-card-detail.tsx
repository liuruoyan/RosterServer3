import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pay-card.reducer';
import { IPayCard } from 'app/shared/model/pay-card.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPayCardDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PayCardDetail extends React.Component<IPayCardDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { payCardEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.payCard.detail.title">PayCard</Translate> [<b>{payCardEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="code">
                <Translate contentKey="rosterServer3App.payCard.code">Code</Translate>
              </span>
              <UncontrolledTooltip target="code">
                <Translate contentKey="rosterServer3App.payCard.help.code" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.code}</dd>
            <dt>
              <span id="branch">
                <Translate contentKey="rosterServer3App.payCard.branch">Branch</Translate>
              </span>
              <UncontrolledTooltip target="branch">
                <Translate contentKey="rosterServer3App.payCard.help.branch" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.branch}</dd>
            <dt>
              <span id="accountName">
                <Translate contentKey="rosterServer3App.payCard.accountName">Account Name</Translate>
              </span>
              <UncontrolledTooltip target="accountName">
                <Translate contentKey="rosterServer3App.payCard.help.accountName" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.accountName}</dd>
            <dt>
              <span id="bankAccount">
                <Translate contentKey="rosterServer3App.payCard.bankAccount">Bank Account</Translate>
              </span>
              <UncontrolledTooltip target="bankAccount">
                <Translate contentKey="rosterServer3App.payCard.help.bankAccount" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.bankAccount}</dd>
            <dt>
              <span id="isSelfVerify">
                <Translate contentKey="rosterServer3App.payCard.isSelfVerify">Is Self Verify</Translate>
              </span>
              <UncontrolledTooltip target="isSelfVerify">
                <Translate contentKey="rosterServer3App.payCard.help.isSelfVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.isSelfVerify ? 'true' : 'false'}</dd>
            <dt>
              <span id="isHrVerify">
                <Translate contentKey="rosterServer3App.payCard.isHrVerify">Is Hr Verify</Translate>
              </span>
              <UncontrolledTooltip target="isHrVerify">
                <Translate contentKey="rosterServer3App.payCard.help.isHrVerify" />
              </UncontrolledTooltip>
            </dt>
            <dd>{payCardEntity.isHrVerify ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.payCard.depositBank">Deposit Bank</Translate>
            </dt>
            <dd>{payCardEntity.depositBankId ? payCardEntity.depositBankId : ''}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.payCard.emp">Emp</Translate>
            </dt>
            <dd>{payCardEntity.empId ? payCardEntity.empId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pay-card" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pay-card/${payCardEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ payCard }: IRootState) => ({
  payCardEntity: payCard.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PayCardDetail);
