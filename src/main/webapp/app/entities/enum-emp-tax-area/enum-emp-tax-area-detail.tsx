import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-emp-tax-area.reducer';
import { IEnumEmpTaxArea } from 'app/shared/model/enum-emp-tax-area.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumEmpTaxAreaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumEmpTaxAreaDetail extends React.Component<IEnumEmpTaxAreaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumEmpTaxAreaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumEmpTaxArea.detail.title">EnumEmpTaxArea</Translate> [
            <b>{enumEmpTaxAreaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumEmpTaxArea.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumEmpTaxAreaEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumEmpTaxArea.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumEmpTaxAreaEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumEmpTaxArea.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumEmpTaxAreaEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumEmpTaxArea.parent">Parent</Translate>
            </dt>
            <dd>{enumEmpTaxAreaEntity.parentId ? enumEmpTaxAreaEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-emp-tax-area" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-emp-tax-area/${enumEmpTaxAreaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumEmpTaxArea }: IRootState) => ({
  enumEmpTaxAreaEntity: enumEmpTaxArea.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumEmpTaxAreaDetail);
