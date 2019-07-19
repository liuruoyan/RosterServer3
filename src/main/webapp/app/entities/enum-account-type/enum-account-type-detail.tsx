import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-account-type.reducer';
import { IEnumAccountType } from 'app/shared/model/enum-account-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumAccountTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumAccountTypeDetail extends React.Component<IEnumAccountTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumAccountTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumAccountType.detail.title">EnumAccountType</Translate> [
            <b>{enumAccountTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumAccountType.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumAccountTypeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumAccountType.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumAccountTypeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumAccountType.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumAccountTypeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumAccountType.parent">Parent</Translate>
            </dt>
            <dd>{enumAccountTypeEntity.parentId ? enumAccountTypeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-account-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-account-type/${enumAccountTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumAccountType }: IRootState) => ({
  enumAccountTypeEntity: enumAccountType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumAccountTypeDetail);
