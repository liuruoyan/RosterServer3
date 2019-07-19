import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getEnumHighestEducations } from 'app/entities/enum-highest-education/enum-highest-education.reducer';
import { getEntity, updateEntity, createEntity, reset } from './enum-highest-education.reducer';
import { IEnumHighestEducation } from 'app/shared/model/enum-highest-education.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEnumHighestEducationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEnumHighestEducationUpdateState {
  isNew: boolean;
  parentId: string;
}

export class EnumHighestEducationUpdate extends React.Component<IEnumHighestEducationUpdateProps, IEnumHighestEducationUpdateState> {
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

    this.props.getEnumHighestEducations();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { enumHighestEducationEntity } = this.props;
      const entity = {
        ...enumHighestEducationEntity,
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
    this.props.history.push('/entity/enum-highest-education');
  };

  render() {
    const { enumHighestEducationEntity, enumHighestEducations, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.enumHighestEducation.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.enumHighestEducation.home.createOrEditLabel">
                Create or edit a EnumHighestEducation
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : enumHighestEducationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="enum-highest-education-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="enum-highest-education-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="valuezLabel" for="enum-highest-education-valuez">
                    <Translate contentKey="rosterServer3App.enumHighestEducation.valuez">Valuez</Translate>
                  </Label>
                  <AvField id="enum-highest-education-valuez" type="text" name="valuez" />
                </AvGroup>
                <AvGroup>
                  <Label id="orderzLabel" for="enum-highest-education-orderz">
                    <Translate contentKey="rosterServer3App.enumHighestEducation.orderz">Orderz</Translate>
                  </Label>
                  <AvField id="enum-highest-education-orderz" type="string" className="form-control" name="orderz" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenentCodeLabel" for="enum-highest-education-tenentCode">
                    <Translate contentKey="rosterServer3App.enumHighestEducation.tenentCode">Tenent Code</Translate>
                  </Label>
                  <AvField id="enum-highest-education-tenentCode" type="text" name="tenentCode" />
                </AvGroup>
                <AvGroup>
                  <Label for="enum-highest-education-parent">
                    <Translate contentKey="rosterServer3App.enumHighestEducation.parent">Parent</Translate>
                  </Label>
                  <AvInput id="enum-highest-education-parent" type="select" className="form-control" name="parentId">
                    <option value="" key="0" />
                    {enumHighestEducations
                      ? enumHighestEducations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/enum-highest-education" replace color="info">
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
  enumHighestEducations: storeState.enumHighestEducation.entities,
  enumHighestEducationEntity: storeState.enumHighestEducation.entity,
  loading: storeState.enumHighestEducation.loading,
  updating: storeState.enumHighestEducation.updating,
  updateSuccess: storeState.enumHighestEducation.updateSuccess
});

const mapDispatchToProps = {
  getEnumHighestEducations,
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
)(EnumHighestEducationUpdate);
