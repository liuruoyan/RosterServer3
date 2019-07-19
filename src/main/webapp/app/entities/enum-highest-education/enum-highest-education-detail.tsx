import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-highest-education.reducer';
import { IEnumHighestEducation } from 'app/shared/model/enum-highest-education.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumHighestEducationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumHighestEducationDetail extends React.Component<IEnumHighestEducationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumHighestEducationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumHighestEducation.detail.title">EnumHighestEducation</Translate> [
            <b>{enumHighestEducationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumHighestEducation.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumHighestEducationEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumHighestEducation.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumHighestEducationEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumHighestEducation.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumHighestEducationEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumHighestEducation.parent">Parent</Translate>
            </dt>
            <dd>{enumHighestEducationEntity.parentId ? enumHighestEducationEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-highest-education" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-highest-education/${enumHighestEducationEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumHighestEducation }: IRootState) => ({
  enumHighestEducationEntity: enumHighestEducation.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumHighestEducationDetail);
