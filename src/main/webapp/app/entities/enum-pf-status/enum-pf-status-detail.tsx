import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-pf-status.reducer';
import { IEnumPfStatus } from 'app/shared/model/enum-pf-status.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumPfStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumPfStatusDetail extends React.Component<IEnumPfStatusDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumPfStatusEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumPfStatus.detail.title">EnumPfStatus</Translate> [<b>{enumPfStatusEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumPfStatus.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumPfStatusEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumPfStatus.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumPfStatusEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumPfStatus.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumPfStatusEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumPfStatus.parent">Parent</Translate>
            </dt>
            <dd>{enumPfStatusEntity.parentId ? enumPfStatusEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-pf-status" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-pf-status/${enumPfStatusEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumPfStatus }: IRootState) => ({
  enumPfStatusEntity: enumPfStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumPfStatusDetail);
