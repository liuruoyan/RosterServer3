import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './work-experience.reducer';
import { IWorkExperience } from 'app/shared/model/work-experience.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IWorkExperienceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IWorkExperienceUpdateState {
  isNew: boolean;
  empId: string;
}

export class WorkExperienceUpdate extends React.Component<IWorkExperienceUpdateProps, IWorkExperienceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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

    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { workExperienceEntity } = this.props;
      const entity = {
        ...workExperienceEntity,
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
    this.props.history.push('/entity/work-experience');
  };

  render() {
    const { workExperienceEntity, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.workExperience.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.workExperience.home.createOrEditLabel">Create or edit a WorkExperience</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : workExperienceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="work-experience-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="work-experience-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="work-experience-code">
                    <Translate contentKey="rosterServer3App.workExperience.code">Code</Translate>
                  </Label>
                  <AvField id="work-experience-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="eNameLabel" for="work-experience-eName">
                    <Translate contentKey="rosterServer3App.workExperience.eName">E Name</Translate>
                  </Label>
                  <AvField id="work-experience-eName" type="text" name="eName" />
                  <UncontrolledTooltip target="eNameLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.eName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneNumLabel" for="work-experience-phoneNum">
                    <Translate contentKey="rosterServer3App.workExperience.phoneNum">Phone Num</Translate>
                  </Label>
                  <AvField id="work-experience-phoneNum" type="text" name="phoneNum" />
                  <UncontrolledTooltip target="phoneNumLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.phoneNum" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="companyLabel" for="work-experience-company">
                    <Translate contentKey="rosterServer3App.workExperience.company">Company</Translate>
                  </Label>
                  <AvField id="work-experience-company" type="text" name="company" />
                  <UncontrolledTooltip target="companyLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.company" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="jobLabel" for="work-experience-job">
                    <Translate contentKey="rosterServer3App.workExperience.job">Job</Translate>
                  </Label>
                  <AvField id="work-experience-job" type="text" name="job" />
                  <UncontrolledTooltip target="jobLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.job" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="jobDescLabel" for="work-experience-jobDesc">
                    <Translate contentKey="rosterServer3App.workExperience.jobDesc">Job Desc</Translate>
                  </Label>
                  <AvField id="work-experience-jobDesc" type="text" name="jobDesc" />
                  <UncontrolledTooltip target="jobDescLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.jobDesc" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="hireDateLabel" for="work-experience-hireDate">
                    <Translate contentKey="rosterServer3App.workExperience.hireDate">Hire Date</Translate>
                  </Label>
                  <AvField id="work-experience-hireDate" type="date" className="form-control" name="hireDate" />
                  <UncontrolledTooltip target="hireDateLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.hireDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="leaveDateLabel" for="work-experience-leaveDate">
                    <Translate contentKey="rosterServer3App.workExperience.leaveDate">Leave Date</Translate>
                  </Label>
                  <AvField id="work-experience-leaveDate" type="date" className="form-control" name="leaveDate" />
                  <UncontrolledTooltip target="leaveDateLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.leaveDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="work-experience-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.workExperience.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="work-experience-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.workExperience.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.workExperience.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="work-experience-emp">
                    <Translate contentKey="rosterServer3App.workExperience.emp">Emp</Translate>
                  </Label>
                  <AvInput id="work-experience-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/work-experience" replace color="info">
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
  employees: storeState.employee.entities,
  workExperienceEntity: storeState.workExperience.entity,
  loading: storeState.workExperience.loading,
  updating: storeState.workExperience.updating,
  updateSuccess: storeState.workExperience.updateSuccess
});

const mapDispatchToProps = {
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
)(WorkExperienceUpdate);
