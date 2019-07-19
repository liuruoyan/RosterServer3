import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumEducations } from 'app/entities/enum-education/enum-education.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-education.reducer';
import { IEnumEducation } from 'app/shared/model/enum-education.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumEducationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumEducationUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumEducationUpdate extends React.Component<IEnumEducationUpdateProps, IEnumEducationUpdateState> {
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

    this.props.getEnumEducations();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumEducationEntity } = this.props;
      const entity = {
        ...enumEducationEntity,
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
    this.props.history.push('/entity/enum-education');
  };

  render() {
    const { enumEducationEntity, enumEducations, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumEducation.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumEducation.home.createOrEditLabel">Create or edit a EnumEducation</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumEducationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-education-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-education-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-education-valuez">
                    <Translate contentKey="rosterServer3App.enumEducation.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-education-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-education-orderz">
                    <Translate contentKey="rosterServer3App.enumEducation.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-education-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-education-tenentCode">
                    <Translate contentKey="rosterServer3App.enumEducation.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-education-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-education-parent">
                    <Translate contentKey="rosterServer3App.enumEducation.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-education-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumEducations
                      ? enumEducations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-education" replace color="info">
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
  enumEducations: storeState.enumEducation.entities,
  enumEducationEntity: storeState.enumEducation.entity,
  loading: storeState.enumEducation.loading,
  updating: storeState.enumEducation.updating,
  updateSuccess: storeState.enumEducation.updateSuccess
});

const mapDispatchToProps = {
  getEnumEducations,
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
)(EnumEducationUpdate);
