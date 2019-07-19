import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-education.reducer';
import { IEnumEducation } from 'app/shared/model/enum-education.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumEducationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumEducationDetail extends React.Component<IEnumEducationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumEducationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumEducation.detail.title">EnumEducation</Translate> [<b>{enumEducationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumEducation.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumEducationEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumEducation.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumEducationEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumEducation.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumEducationEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumEducation.parent">Parent</Translate>
            </dt>
            <dd>{enumEducationEntity.parentId ? enumEducationEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-education" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-education/${enumEducationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumEducation }: IRootState) => ({
  enumEducationEntity: enumEducation.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumEducationDetail);
