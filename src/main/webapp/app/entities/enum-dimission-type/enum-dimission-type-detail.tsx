import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-dimission-type.reducer';
import { IEnumDimissionType } from 'app/shared/model/enum-dimission-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumDimissionTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumDimissionTypeDetail extends React.Component<IEnumDimissionTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumDimissionTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumDimissionType.detail.title">EnumDimissionType</Translate> [
            <b>{enumDimissionTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumDimissionType.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumDimissionTypeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumDimissionType.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumDimissionTypeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumDimissionType.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumDimissionTypeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumDimissionType.parent">Parent</Translate>
            </dt>
            <dd>{enumDimissionTypeEntity.parentId ? enumDimissionTypeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-dimission-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-dimission-type/${enumDimissionTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumDimissionType }: IRootState) => ({
  enumDimissionTypeEntity: enumDimissionType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumDimissionTypeDetail);
