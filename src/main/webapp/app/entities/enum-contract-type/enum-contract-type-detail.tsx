import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-contract-type.reducer';
import { IEnumContractType } from 'app/shared/model/enum-contract-type.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumContractTypeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumContractTypeDetail extends React.Component<IEnumContractTypeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumContractTypeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumContractType.detail.title">EnumContractType</Translate> [
            <b>{enumContractTypeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumContractType.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumContractTypeEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumContractType.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumContractTypeEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumContractType.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumContractTypeEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumContractType.parent">Parent</Translate>
            </dt>
            <dd>{enumContractTypeEntity.parentId ? enumContractTypeEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-contract-type" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-contract-type/${enumContractTypeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumContractType }: IRootState) => ({
  enumContractTypeEntity: enumContractType.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumContractTypeDetail);
