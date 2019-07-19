import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-pf-pay-scheme.reducer';
import { IEnumPfPayScheme } from 'app/shared/model/enum-pf-pay-scheme.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumPfPaySchemeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumPfPaySchemeDetail extends React.Component<IEnumPfPaySchemeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumPfPaySchemeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumPfPayScheme.detail.title">EnumPfPayScheme</Translate> [
            <b>{enumPfPaySchemeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumPfPayScheme.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumPfPaySchemeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumPfPayScheme.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumPfPaySchemeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumPfPayScheme.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumPfPaySchemeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumPfPayScheme.parent">Parent</Translate>
            </dt>
            <dd>{enumPfPaySchemeEntity.parentId ? enumPfPaySchemeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-pf-pay-scheme" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-pf-pay-scheme/${enumPfPaySchemeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumPfPayScheme }: IRootState) => ({
  enumPfPaySchemeEntity: enumPfPayScheme.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPfPaySchemeDetail);
