import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-ss-status.reducer';
import { IEnumSsStatus } from 'app/shared/model/enum-ss-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumSsStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumSsStatusDetail extends React.Component<IEnumSsStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumSsStatusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumSsStatus.detail.title">EnumSsStatus</Translate> [<b>{enumSsStatusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumSsStatus.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumSsStatusEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumSsStatus.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumSsStatusEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumSsStatus.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumSsStatusEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumSsStatus.parent">Parent</Translate>
            </dt>
            <dd>{enumSsStatusEntity.parentId ? enumSsStatusEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-ss-status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-ss-status/${enumSsStatusEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumSsStatus }: IRootState) => ({
  enumSsStatusEntity: enumSsStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumSsStatusDetail);
