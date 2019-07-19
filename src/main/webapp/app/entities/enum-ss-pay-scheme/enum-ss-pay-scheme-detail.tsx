import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-ss-pay-scheme.reducer';
import { IEnumSsPayScheme } from 'app/shared/model/enum-ss-pay-scheme.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumSsPaySchemeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumSsPaySchemeDetail extends React.Component<IEnumSsPaySchemeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumSsPaySchemeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumSsPayScheme.detail.title">EnumSsPayScheme</Translate> [
            <b>{enumSsPaySchemeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumSsPayScheme.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumSsPaySchemeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumSsPayScheme.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumSsPaySchemeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumSsPayScheme.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumSsPaySchemeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumSsPayScheme.parent">Parent</Translate>
            </dt>
            <dd>{enumSsPaySchemeEntity.parentId ? enumSsPaySchemeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-ss-pay-scheme" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-ss-pay-scheme/${enumSsPaySchemeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumSsPayScheme }: IRootState) => ({
  enumSsPaySchemeEntity: enumSsPayScheme.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumSsPaySchemeDetail);
