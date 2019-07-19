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
import { getEntity, updateEntity, createEntity, reset } from './additional-post.reducer';
import { IAdditionalPost } from 'app/shared/model/additional-post.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdditionalPostUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAdditionalPostUpdateState {
  isNew: boolean;
  empId: string;
}

export class AdditionalPostUpdate extends React.Component<IAdditionalPostUpdateProps, IAdditionalPostUpdateState> {
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
      const { additionalPostEntity } = this.props;
      const entity = {
        ...additionalPostEntity,
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
    this.props.history.push('/entity/additional-post');
  };

  render() {
    const { additionalPostEntity, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.additionalPost.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.additionalPost.home.createOrEditLabel">Create or edit a AdditionalPost</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : additionalPostEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="additional-post-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="additional-post-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="additional-post-code">
                    <Translate contentKey="rosterServer3App.additionalPost.code">Code</Translate>
                  </Label>
                  <AvField id="additional-post-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="additional-post-name">
                    <Translate contentKey="rosterServer3App.additionalPost.name">Name</Translate>
                  </Label>
                  <AvField id="additional-post-name" type="text" name="name" />
                  <UncontrolledTooltip target="nameLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.name" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="additional-post-phone">
                    <Translate contentKey="rosterServer3App.additionalPost.phone">Phone</Translate>
                  </Label>
                  <AvField id="additional-post-phone" type="text" name="phone" />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="deptLabel" for="additional-post-dept">
                    <Translate contentKey="rosterServer3App.additionalPost.dept">Dept</Translate>
                  </Label>
                  <AvField id="additional-post-dept" type="text" name="dept" />
                  <UncontrolledTooltip target="deptLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.dept" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="jobLabel" for="additional-post-job">
                    <Translate contentKey="rosterServer3App.additionalPost.job">Job</Translate>
                  </Label>
                  <AvField id="additional-post-job" type="text" name="job" />
                  <UncontrolledTooltip target="jobLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.job" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="startDateLabel" for="additional-post-startDate">
                    <Translate contentKey="rosterServer3App.additionalPost.startDate">Start Date</Translate>
                  </Label>
                  <AvField id="additional-post-startDate" type="date" className="form-control" name="startDate" />
                  <UncontrolledTooltip target="startDateLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.startDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="endDateLabel" for="additional-post-endDate">
                    <Translate contentKey="rosterServer3App.additionalPost.endDate">End Date</Translate>
                  </Label>
                  <AvField id="additional-post-endDate" type="date" className="form-control" name="endDate" />
                  <UncontrolledTooltip target="endDateLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.endDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarkLabel" for="additional-post-remark">
                    <Translate contentKey="rosterServer3App.additionalPost.remark">Remark</Translate>
                  </Label>
                  <AvField id="additional-post-remark" type="text" name="remark" />
                  <UncontrolledTooltip target="remarkLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.remark" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="additional-post-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.additionalPost.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="additional-post-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.additionalPost.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.additionalPost.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="additional-post-emp">
                    <Translate contentKey="rosterServer3App.additionalPost.emp">Emp</Translate>
                  </Label>
                  <AvInput id="additional-post-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/additional-post" replace color="info">
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
  additionalPostEntity: storeState.additionalPost.entity,
  loading: storeState.additionalPost.loading,
  updating: storeState.additionalPost.updating,
  updateSuccess: storeState.additionalPost.updateSuccess
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
)(AdditionalPostUpdate);
