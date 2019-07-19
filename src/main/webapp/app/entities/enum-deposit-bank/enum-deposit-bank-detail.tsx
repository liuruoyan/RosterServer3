import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-deposit-bank.reducer';
import { IEnumDepositBank } from 'app/shared/model/enum-deposit-bank.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumDepositBankDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumDepositBankDetail extends React.Component<IEnumDepositBankDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumDepositBankEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumDepositBank.detail.title">EnumDepositBank</Translate> [
            <b>{enumDepositBankEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumDepositBank.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumDepositBankEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumDepositBank.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumDepositBankEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumDepositBank.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumDepositBankEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumDepositBank.parent">Parent</Translate>
            </dt>
            <dd>{enumDepositBankEntity.parentId ? enumDepositBankEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-deposit-bank" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-deposit-bank/${enumDepositBankEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumDepositBank }: IRootState) => ({
  enumDepositBankEntity: enumDepositBank.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumDepositBankDetail);
