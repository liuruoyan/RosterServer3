import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './enum-gender.reducer';
import { IEnumGender } from 'app/shared/model/enum-gender.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEnumGenderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EnumGenderDetail extends React.Component<IEnumGenderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { enumGenderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="rosterServer3App.enumGender.detail.title">EnumGender</Translate> [<b>{enumGenderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="valuez">
                <Translate contentKey="rosterServer3App.enumGender.valuez">Valuez</Translate>
              </span>
            </dt>
            <dd>{enumGenderEntity.valuez}</dd>
            <dt>
              <span id="orderz">
                <Translate contentKey="rosterServer3App.enumGender.orderz">Orderz</Translate>
              </span>
            </dt>
            <dd>{enumGenderEntity.orderz}</dd>
            <dt>
              <span id="tenentCode">
                <Translate contentKey="rosterServer3App.enumGender.tenentCode">Tenent Code</Translate>
              </span>
            </dt>
            <dd>{enumGenderEntity.tenentCode}</dd>
            <dt>
              <Translate contentKey="rosterServer3App.enumGender.parent">Parent</Translate>
            </dt>
            <dd>{enumGenderEntity.parentId ? enumGenderEntity.parentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/enum-gender" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/enum-gender/${enumGenderEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ enumGender }: IRootState) => ({
  enumGenderEntity: enumGender.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumGenderDetail);
