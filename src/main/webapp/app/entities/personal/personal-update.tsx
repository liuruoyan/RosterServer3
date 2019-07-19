import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumAccountType } from 'app/shared/model/enum-account-type.model';
import { getEntities as getEnumAccountTypes } from 'app/entities/enum-account-type/enum-account-type.reducer';
import { IEnumHighestEducation } from 'app/shared/model/enum-highest-education.model';
import { getEntities as getEnumHighestEducations } from 'app/entities/enum-highest-education/enum-highest-education.reducer';
import { IEnumPoliticsStatus } from 'app/shared/model/enum-politics-status.model';
import { getEntities as getEnumPoliticsStatuses } from 'app/entities/enum-politics-status/enum-politics-status.reducer';
import { IEnumMaritalStatus } from 'app/shared/model/enum-marital-status.model';
import { getEntities as getEnumMaritalStatuses } from 'app/entities/enum-marital-status/enum-marital-status.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './personal.reducer';
import { IPersonal } from 'app/shared/model/personal.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPersonalUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPersonalUpdateState {
  isNew: boolean;
  accountTypeId: string;
  highestEducationId: string;
  politicsStatusId: string;
  maritalStatusId: string;
  empId: string;
}

export class PersonalUpdate extends React.Component<IPersonalUpdateProps, IPersonalUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      accountTypeId: '0',
      highestEducationId: '0',
      politicsStatusId: '0',
      maritalStatusId: '0',
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

    this.props.getEnumAccountTypes();
    this.props.getEnumHighestEducations();
    this.props.getEnumPoliticsStatuses();
    this.props.getEnumMaritalStatuses();
    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { personalEntity } = this.props;
      const entity = {
        ...personalEntity,
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
    this.props.history.push('/entity/personal');
  };

  render() {
    const {
      personalEntity,
      enumAccountTypes,
      enumHighestEducations,
      enumPoliticsStatuses,
      enumMaritalStatuses,
      employees,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.personal.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.personal.home.createOrEditLabel">Create or edit a Personal</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : personalEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="personal-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="personal-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="personal-code">
                    <Translate contentKey="rosterServer3App.personal.code">Code</Translate>
                  </Label>
                  <AvField id="personal-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.personal.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="stageNameLabel" for="personal-stageName">
                    <Translate contentKey="rosterServer3App.personal.stageName">Stage Name</Translate>
                  </Label>
                  <AvField id="personal-stageName" type="text" name="stageName" />
                  <UncontrolledTooltip target="stageNameLabel">
                    <Translate contentKey="rosterServer3App.personal.help.stageName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="idNameLabel" for="personal-idName">
                    <Translate contentKey="rosterServer3App.personal.idName">Id Name</Translate>
                  </Label>
                  <AvField id="personal-idName" type="text" name="idName" />
                  <UncontrolledTooltip target="idNameLabel">
                    <Translate contentKey="rosterServer3App.personal.help.idName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nationLabel" for="personal-nation">
                    <Translate contentKey="rosterServer3App.personal.nation">Nation</Translate>
                  </Label>
                  <AvField id="personal-nation" type="text" name="nation" />
                  <UncontrolledTooltip target="nationLabel">
                    <Translate contentKey="rosterServer3App.personal.help.nation" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="accountLocLabel" for="personal-accountLoc">
                    <Translate contentKey="rosterServer3App.personal.accountLoc">Account Loc</Translate>
                  </Label>
                  <AvField id="personal-accountLoc" type="text" name="accountLoc" />
                  <UncontrolledTooltip target="accountLocLabel">
                    <Translate contentKey="rosterServer3App.personal.help.accountLoc" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="nativePlaceLabel" for="personal-nativePlace">
                    <Translate contentKey="rosterServer3App.personal.nativePlace">Native Place</Translate>
                  </Label>
                  <AvField id="personal-nativePlace" type="text" name="nativePlace" />
                  <UncontrolledTooltip target="nativePlaceLabel">
                    <Translate contentKey="rosterServer3App.personal.help.nativePlace" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="currentAddrLabel" for="personal-currentAddr">
                    <Translate contentKey="rosterServer3App.personal.currentAddr">Current Addr</Translate>
                  </Label>
                  <AvField id="personal-currentAddr" type="text" name="currentAddr" />
                  <UncontrolledTooltip target="currentAddrLabel">
                    <Translate contentKey="rosterServer3App.personal.help.currentAddr" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="spouseNameLabel" for="personal-spouseName">
                    <Translate contentKey="rosterServer3App.personal.spouseName">Spouse Name</Translate>
                  </Label>
                  <AvField id="personal-spouseName" type="text" name="spouseName" />
                  <UncontrolledTooltip target="spouseNameLabel">
                    <Translate contentKey="rosterServer3App.personal.help.spouseName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="childNameLabel" for="personal-childName">
                    <Translate contentKey="rosterServer3App.personal.childName">Child Name</Translate>
                  </Label>
                  <AvField id="personal-childName" type="text" name="childName" />
                  <UncontrolledTooltip target="childNameLabel">
                    <Translate contentKey="rosterServer3App.personal.help.childName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="bloodTypeLabel" for="personal-bloodType">
                    <Translate contentKey="rosterServer3App.personal.bloodType">Blood Type</Translate>
                  </Label>
                  <AvInput
                    id="personal-bloodType"
                    type="select"
                    className="form-control"
                    name="bloodType"
                    value={(!isNew && personalEntity.bloodType) || 'A'}
                  >
                    <option value="A">{translate('rosterServer3App.BloodType.A')}</option>
                    <option value="B">{translate('rosterServer3App.BloodType.B')}</option>
                    <option value="O">{translate('rosterServer3App.BloodType.O')}</option>
                    <option value="AB">{translate('rosterServer3App.BloodType.AB')}</option>
                  </AvInput>
                  <UncontrolledTooltip target="bloodTypeLabel">
                    <Translate contentKey="rosterServer3App.personal.help.bloodType" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactNameLabel" for="personal-emergencyContactName">
                    <Translate contentKey="rosterServer3App.personal.emergencyContactName">Emergency Contact Name</Translate>
                  </Label>
                  <AvField id="personal-emergencyContactName" type="text" name="emergencyContactName" />
                  <UncontrolledTooltip target="emergencyContactNameLabel">
                    <Translate contentKey="rosterServer3App.personal.help.emergencyContactName" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="emergencyContactPhoneLabel" for="personal-emergencyContactPhone">
                    <Translate contentKey="rosterServer3App.personal.emergencyContactPhone">Emergency Contact Phone</Translate>
                  </Label>
                  <AvField id="personal-emergencyContactPhone" type="text" name="emergencyContactPhone" />
                  <UncontrolledTooltip target="emergencyContactPhoneLabel">
                    <Translate contentKey="rosterServer3App.personal.help.emergencyContactPhone" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="qqLabel" for="personal-qq">
                    <Translate contentKey="rosterServer3App.personal.qq">Qq</Translate>
                  </Label>
                  <AvField id="personal-qq" type="text" name="qq" />
                  <UncontrolledTooltip target="qqLabel">
                    <Translate contentKey="rosterServer3App.personal.help.qq" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="wechatLabel" for="personal-wechat">
                    <Translate contentKey="rosterServer3App.personal.wechat">Wechat</Translate>
                  </Label>
                  <AvField id="personal-wechat" type="text" name="wechat" />
                  <UncontrolledTooltip target="wechatLabel">
                    <Translate contentKey="rosterServer3App.personal.help.wechat" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="personalEmailLabel" for="personal-personalEmail">
                    <Translate contentKey="rosterServer3App.personal.personalEmail">Personal Email</Translate>
                  </Label>
                  <AvField id="personal-personalEmail" type="text" name="personalEmail" />
                  <UncontrolledTooltip target="personalEmailLabel">
                    <Translate contentKey="rosterServer3App.personal.help.personalEmail" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="remarkLabel" for="personal-remark">
                    <Translate contentKey="rosterServer3App.personal.remark">Remark</Translate>
                  </Label>
                  <AvField id="personal-remark" type="text" name="remark" />
                  <UncontrolledTooltip target="remarkLabel">
                    <Translate contentKey="rosterServer3App.personal.help.remark" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="othersLabel" for="personal-others">
                    <Translate contentKey="rosterServer3App.personal.others">Others</Translate>
                  </Label>
                  <AvField id="personal-others" type="text" name="others" />
                  <UncontrolledTooltip target="othersLabel">
                    <Translate contentKey="rosterServer3App.personal.help.others" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="personal-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.personal.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.personal.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="personal-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.personal.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.personal.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="personal-accountType">
                    <Translate contentKey="rosterServer3App.personal.accountType">Account Type</Translate>
                  </Label>
                  <AvInput id="personal-accountType" type="select" className="form-control" name="accountTypeId">
                    <option value="" key="0" />
                    {enumAccountTypes
                      ? enumAccountTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="personal-highestEducation">
                    <Translate contentKey="rosterServer3App.personal.highestEducation">Highest Education</Translate>
                  </Label>
                  <AvInput id="personal-highestEducation" type="select" className="form-control" name="highestEducationId">
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
                <AvGroup>
                  <Label for="personal-politicsStatus">
                    <Translate contentKey="rosterServer3App.personal.politicsStatus">Politics Status</Translate>
                  </Label>
                  <AvInput id="personal-politicsStatus" type="select" className="form-control" name="politicsStatusId">
                    <option value="" key="0" />
                    {enumPoliticsStatuses
                      ? enumPoliticsStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="personal-maritalStatus">
                    <Translate contentKey="rosterServer3App.personal.maritalStatus">Marital Status</Translate>
                  </Label>
                  <AvInput id="personal-maritalStatus" type="select" className="form-control" name="maritalStatusId">
                    <option value="" key="0" />
                    {enumMaritalStatuses
                      ? enumMaritalStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="personal-emp">
                    <Translate contentKey="rosterServer3App.personal.emp">Emp</Translate>
                  </Label>
                  <AvInput id="personal-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/personal" replace color="info">
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
  enumAccountTypes: storeState.enumAccountType.entities,
  enumHighestEducations: storeState.enumHighestEducation.entities,
  enumPoliticsStatuses: storeState.enumPoliticsStatus.entities,
  enumMaritalStatuses: storeState.enumMaritalStatus.entities,
  employees: storeState.employee.entities,
  personalEntity: storeState.personal.entity,
  loading: storeState.personal.loading,
  updating: storeState.personal.updating,
  updateSuccess: storeState.personal.updateSuccess
});

const mapDispatchToProps = {
  getEnumAccountTypes,
  getEnumHighestEducations,
  getEnumPoliticsStatuses,
  getEnumMaritalStatuses,
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
)(PersonalUpdate);
