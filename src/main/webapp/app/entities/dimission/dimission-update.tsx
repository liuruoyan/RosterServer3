import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumDimissionType } from 'app/shared/model/enum-dimission-type.model';
import { getEntities as getEnumDimissionTypes } from 'app/entities/enum-dimission-type/enum-dimission-type.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './dimission.reducer';
import { IDimission } from 'app/shared/model/dimission.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDimissionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDimissionUpdateState {
  isNew: boolean;
  dimissionTypeId: string;
  empId: string;
}

export class DimissionUpdate extends React.Component<IDimissionUpdateProps, IDimissionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      dimissionTypeId: '0',
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

    this.props.getEnumDimissionTypes();
    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { dimissionEntity } = this.props;
      const entity = {
        ...dimissionEntity,
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
    this.props.history.push('/entity/dimission');
  };

  render() {
    const { dimissionEntity, enumDimissionTypes, employees, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.dimission.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.dimission.home.createOrEditLabel">Create or edit a Dimission</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : dimissionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="dimission-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="dimission-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="dimission-code">
                    <Translate contentKey="rosterServer3App.dimission.code">Code</Translate>
                  </Label>
                  <AvField id="dimission-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.dimission.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="lastDateLabel" for="dimission-lastDate">
                    <Translate contentKey="rosterServer3App.dimission.lastDate">Last Date</Translate>
                  </Label>
                  <AvField id="dimission-lastDate" type="date" className="form-control" name="lastDate" />
                  <UncontrolledTooltip target="lastDateLabel">
                    <Translate contentKey="rosterServer3App.dimission.help.lastDate" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="reasonLabel" for="dimission-reason">
                    <Translate contentKey="rosterServer3App.dimission.reason">Reason</Translate>
                  </Label>
                  <AvField id="dimission-reason" type="text" name="reason" />
                  <UncontrolledTooltip target="reasonLabel">
                    <Translate contentKey="rosterServer3App.dimission.help.reason" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="dimission-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.dimission.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.dimission.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="dimission-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.dimission.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.dimission.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="dimission-dimissionType">
                    <Translate contentKey="rosterServer3App.dimission.dimissionType">Dimission Type</Translate>
                  </Label>
                  <AvInput id="dimission-dimissionType" type="select" className="form-control" name="dimissionTypeId">
                    <option value="" key="0" />
                    {enumDimissionTypes
                      ? enumDimissionTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="dimission-emp">
                    <Translate contentKey="rosterServer3App.dimission.emp">Emp</Translate>
                  </Label>
                  <AvInput id="dimission-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/dimission" replace color="info">
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
  enumDimissionTypes: storeState.enumDimissionType.entities,
  employees: storeState.employee.entities,
  dimissionEntity: storeState.dimission.entity,
  loading: storeState.dimission.loading,
  updating: storeState.dimission.updating,
  updateSuccess: storeState.dimission.updateSuccess
});

const mapDispatchToProps = {
  getEnumDimissionTypes,
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
)(DimissionUpdate);
