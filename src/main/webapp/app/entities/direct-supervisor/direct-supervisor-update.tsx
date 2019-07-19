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
import { getEntity, updateEntity, createEntity, reset } from './direct-supervisor.reducer';
import { IDirectSupervisor } from 'app/shared/model/direct-supervisor.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDirectSupervisorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDirectSupervisorUpdateState {
  isNew: boolean;
  empId: string;
}

export class DirectSupervisorUpdate extends React.Component<IDirectSupervisorUpdateProps, IDirectSupervisorUpdateState> {
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
      const { directSupervisorEntity } = this.props;
      const entity = {
        ...directSupervisorEntity,
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
    this.props.history.push('/entity/direct-supervisor');
  };

  render() {
    const { directSupervisorEntity, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.directSupervisor.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.directSupervisor.home.createOrEditLabel">Create or edit a DirectSupervisor</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : directSupervisorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="direct-supervisor-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="direct-supervisor-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="direct-supervisor-code">
                    <Translate contentKey="rosterServer3App.directSupervisor.code">Code</Translate>
                  </Label>
                  <AvField id="direct-supervisor-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="direct-supervisor-name">
                    <Translate contentKey="rosterServer3App.directSupervisor.name">Name</Translate>
                  </Label>
                  <AvField id="direct-supervisor-name" type="text" name="name" />
                  <UncontrolledTooltip target="nameLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.name" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="direct-supervisor-phone">
                    <Translate contentKey="rosterServer3App.directSupervisor.phone">Phone</Translate>
                  </Label>
                  <AvField id="direct-supervisor-phone" type="text" name="phone" />
                  <UncontrolledTooltip target="phoneLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.phone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="aSupNameLabel" for="direct-supervisor-aSupName">
                    <Translate contentKey="rosterServer3App.directSupervisor.aSupName">A Sup Name</Translate>
                  </Label>
                  <AvField id="direct-supervisor-aSupName" type="text" name="aSupName" />
                  <UncontrolledTooltip target="aSupNameLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.aSupName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="aSupPhoneLabel" for="direct-supervisor-aSupPhone">
                    <Translate contentKey="rosterServer3App.directSupervisor.aSupPhone">A Sup Phone</Translate>
                  </Label>
                  <AvField id="direct-supervisor-aSupPhone" type="text" name="aSupPhone" />
                  <UncontrolledTooltip target="aSupPhoneLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.aSupPhone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bSupNameLabel" for="direct-supervisor-bSupName">
                    <Translate contentKey="rosterServer3App.directSupervisor.bSupName">B Sup Name</Translate>
                  </Label>
                  <AvField id="direct-supervisor-bSupName" type="text" name="bSupName" />
                  <UncontrolledTooltip target="bSupNameLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.bSupName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bSupPhoneLabel" for="direct-supervisor-bSupPhone">
                    <Translate contentKey="rosterServer3App.directSupervisor.bSupPhone">B Sup Phone</Translate>
                  </Label>
                  <AvField id="direct-supervisor-bSupPhone" type="text" name="bSupPhone" />
                  <UncontrolledTooltip target="bSupPhoneLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.bSupPhone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="fSubNameLabel" for="direct-supervisor-fSubName">
                    <Translate contentKey="rosterServer3App.directSupervisor.fSubName">F Sub Name</Translate>
                  </Label>
                  <AvField id="direct-supervisor-fSubName" type="text" name="fSubName" />
                  <UncontrolledTooltip target="fSubNameLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.fSubName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="fSubPhoneLabel" for="direct-supervisor-fSubPhone">
                    <Translate contentKey="rosterServer3App.directSupervisor.fSubPhone">F Sub Phone</Translate>
                  </Label>
                  <AvField id="direct-supervisor-fSubPhone" type="text" name="fSubPhone" />
                  <UncontrolledTooltip target="fSubPhoneLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.fSubPhone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="direct-supervisor-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.directSupervisor.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="direct-supervisor-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.directSupervisor.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.directSupervisor.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="direct-supervisor-emp">
                    <Translate contentKey="rosterServer3App.directSupervisor.emp">Emp</Translate>
                  </Label>
                  <AvInput id="direct-supervisor-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/direct-supervisor" replace color="info">
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
  directSupervisorEntity: storeState.directSupervisor.entity,
  loading: storeState.directSupervisor.loading,
  updating: storeState.directSupervisor.updating,
  updateSuccess: storeState.directSupervisor.updateSuccess
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
)(DirectSupervisorUpdate);
