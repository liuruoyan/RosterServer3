import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumEducation } from 'app/shared/model/enum-education.model';
import { getEntities as getEnumEducations } from 'app/entities/enum-education/enum-education.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './education-experience.reducer';
import { IEducationExperience } from 'app/shared/model/education-experience.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEducationExperienceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEducationExperienceUpdateState {
  isNew: boolean;
  educationId: string;
  empId: string;
}

export class EducationExperienceUpdate extends React.Component<IEducationExperienceUpdateProps, IEducationExperienceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      educationId: '0',
      empId: '0',
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
    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { educationExperienceEntity } = this.props;
      const entity = {
        ...educationExperienceEntity,
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
    this.props.history.push('/entity/education-experience');
  };

  render() {
    const { educationExperienceEntity, enumEducations, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.educationExperience.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.educationExperience.home.createOrEditLabel">
                Create or edit a EducationExperience
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : educationExperienceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="education-experience-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="education-experience-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="education-experience-code">
                    <Translate contentKey="rosterServer3App.educationExperience.code">Code</Translate>
                  </Label>
                  <AvField id="education-experience-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="education-experience-name">
                    <Translate contentKey="rosterServer3App.educationExperience.name">Name</Translate>
                  </Label>
                  <AvField id="education-experience-name" type="text" name="name" />
                  <UncontrolledTooltip target="nameLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.name" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="education-experience-phone">
                    <Translate contentKey="rosterServer3App.educationExperience.phone">Phone</Translate>
                  </Label>
                  <AvField id="education-experience-phone" type="text" name="phone" />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="schoolLabel" for="education-experience-school">
                    <Translate contentKey="rosterServer3App.educationExperience.school">School</Translate>
                  </Label>
                  <AvField id="education-experience-school" type="text" name="school" />
                  <UncontrolledTooltip target="schoolLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.school" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="majorLabel" for="education-experience-major">
                    <Translate contentKey="rosterServer3App.educationExperience.major">Major</Translate>
                  </Label>
                  <AvField id="education-experience-major" type="text" name="major" />
                  <UncontrolledTooltip target="majorLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.major" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="inDateLabel" for="education-experience-inDate">
                    <Translate contentKey="rosterServer3App.educationExperience.inDate">In Date</Translate>
                  </Label>
                  <AvField id="education-experience-inDate" type="date" className="form-control" name="inDate" />
                  <UncontrolledTooltip target="inDateLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.inDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="graduationDateLabel" for="education-experience-graduationDate">
                    <Translate contentKey="rosterServer3App.educationExperience.graduationDate">Graduation Date</Translate>
                  </Label>
                  <AvField id="education-experience-graduationDate" type="date" className="form-control" name="graduationDate" />
                  <UncontrolledTooltip target="graduationDateLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.graduationDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="inceptionLabel" check>
                    <AvInput id="education-experience-inception" type="checkbox" className="form-control" name="inception" />
                    <Translate contentKey="rosterServer3App.educationExperience.inception">Inception</Translate>
                  </Label>
                  <UncontrolledTooltip target="inceptionLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.inception" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="education-experience-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.educationExperience.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="education-experience-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.educationExperience.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.educationExperience.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="education-experience-education">
                    <Translate contentKey="rosterServer3App.educationExperience.education">Education</Translate>
                  </Label>
                  <AvInput id="education-experience-education" type="select" className="form-control" name="educationId">
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
                <AvGroup>
                  <Label for="education-experience-emp">
                    <Translate contentKey="rosterServer3App.educationExperience.emp">Emp</Translate>
                  </Label>
                  <AvInput id="education-experience-emp" type="select" className="form-control" name="empId">
                    <option value="" key="0" />
                    {employees
                      ? employees.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/education-experience" replace color="info">
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
  employees: storeState.employee.entities,
  educationExperienceEntity: storeState.educationExperience.entity,
  loading: storeState.educationExperience.loading,
  updating: storeState.educationExperience.updating,
  updateSuccess: storeState.educationExperience.updateSuccess
});

const mapDispatchToProps = {
  getEnumEducations,
  getEmployees,
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
)(EducationExperienceUpdate);
