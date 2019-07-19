import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-marital-status.reducer';
import { IEnumMaritalStatus } from 'app/shared/model/enum-marital-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumMaritalStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumMaritalStatusDetail extends React.Component<IEnumMaritalStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumMaritalStatusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumMaritalStatus.detail.title">EnumMaritalStatus</Translate> [
            <b>{enumMaritalStatusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumMaritalStatus.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumMaritalStatusEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumMaritalStatus.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumMaritalStatusEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumMaritalStatus.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumMaritalStatusEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumMaritalStatus.parent">Parent</Translate>
            </dt>
            <dd>{enumMaritalStatusEntity.parentId ? enumMaritalStatusEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-marital-status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-marital-status/${enumMaritalStatusEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumMaritalStatus }: IRootState) => ({
  enumMaritalStatusEntity: enumMaritalStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumMaritalStatusDetail);
