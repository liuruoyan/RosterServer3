import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-politics-status.reducer';
import { IEnumPoliticsStatus } from 'app/shared/model/enum-politics-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumPoliticsStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumPoliticsStatusDetail extends React.Component<IEnumPoliticsStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumPoliticsStatusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumPoliticsStatus.detail.title">EnumPoliticsStatus</Translate> [
            <b>{enumPoliticsStatusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumPoliticsStatus.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumPoliticsStatusEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumPoliticsStatus.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumPoliticsStatusEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumPoliticsStatus.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumPoliticsStatusEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumPoliticsStatus.parent">Parent</Translate>
            </dt>
            <dd>{enumPoliticsStatusEntity.parentId ? enumPoliticsStatusEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-politics-status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-politics-status/${enumPoliticsStatusEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumPoliticsStatus }: IRootState) => ({
  enumPoliticsStatusEntity: enumPoliticsStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPoliticsStatusDetail);
