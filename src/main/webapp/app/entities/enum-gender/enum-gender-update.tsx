import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumGenders } from 'app/entities/enum-gender/enum-gender.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-gender.reducer';
import { IEnumGender } from 'app/shared/model/enum-gender.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumGenderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumGenderUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumGenderUpdate extends React.Component<IEnumGenderUpdateProps, IEnumGenderUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      parentId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getEnumGenders();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumGenderEntity } = this.props;
      const entity = {
        ...enumGenderEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/enum-gender');
  };

  render() {
    const { enumGenderEntity, enumGenders, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumGender.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumGender.home.createOrEditLabel">Create or edit a EnumGender</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumGenderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-gender-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-gender-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-gender-valuez">
                    <Translate contentKey="rosterServer3App.enumGender.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-gender-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-gender-orderz">
                    <Translate contentKey="rosterServer3App.enumGender.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-gender-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-gender-tenentCode">
                    <Translate contentKey="rosterServer3App.enumGender.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-gender-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-gender-parent">
                    <Translate contentKey="rosterServer3App.enumGender.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-gender-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumGenders
                      ? enumGenders.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-gender" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  enumGenders: storeState.enumGender.entities,
  enumGenderEntity: storeState.enumGender.entity,
  loading: storeState.enumGender.loading,
  updating: storeState.enumGender.updating,
  updateSuccess: storeState.enumGender.updateSuccess
});

const mapDispatchToProps = {
  getEnumGenders,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EnumGenderUpdate);
