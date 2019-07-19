import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-emp-type.reducer';
import { IEnumEmpType } from 'app/shared/model/enum-emp-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumEmpTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumEmpTypeDetail extends React.Component<IEnumEmpTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumEmpTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumEmpType.detail.title">EnumEmpType</Translate> [<b>{enumEmpTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumEmpType.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumEmpTypeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumEmpType.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumEmpTypeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumEmpType.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumEmpTypeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumEmpType.parent">Parent</Translate>
            </dt>
            <dd>{enumEmpTypeEntity.parentId ? enumEmpTypeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-emp-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-emp-type/${enumEmpTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumEmpType }: IRootState) => ({
  enumEmpTypeEntity: enumEmpType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumEmpTypeDetail);
