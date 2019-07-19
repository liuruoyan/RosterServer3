import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEnumPfType } from 'app/shared/model/enum-pf-type.model';
import { getEntities as getEnumPfTypes } from 'app/entities/enum-pf-type/enum-pf-type.reducer';
import { IEnumPfStatus } from 'app/shared/model/enum-pf-status.model';
import { getEntities as getEnumPfStatuses } from 'app/entities/enum-pf-status/enum-pf-status.reducer';
import { IEnumPfPayScheme } from 'app/shared/model/enum-pf-pay-scheme.model';
import { getEntities as getEnumPfPaySchemes } from 'app/entities/enum-pf-pay-scheme/enum-pf-pay-scheme.reducer';
import { IEnumSsPayScheme } from 'app/shared/model/enum-ss-pay-scheme.model';
import { getEntities as getEnumSsPaySchemes } from 'app/entities/enum-ss-pay-scheme/enum-ss-pay-scheme.reducer';
import { IEnumSsStatus } from 'app/shared/model/enum-ss-status.model';
import { getEntities as getEnumSsStatuses } from 'app/entities/enum-ss-status/enum-ss-status.reducer';
import { IEnumEmpLaborType } from 'app/shared/model/enum-emp-labor-type.model';
import { getEntities as getEnumEmpLaborTypes } from 'app/entities/enum-emp-labor-type/enum-emp-labor-type.reducer';
import { IEnumEmpTaxerType } from 'app/shared/model/enum-emp-taxer-type.model';
import { getEntities as getEnumEmpTaxerTypes } from 'app/entities/enum-emp-taxer-type/enum-emp-taxer-type.reducer';
import { IEnumEmpTaxArea } from 'app/shared/model/enum-emp-tax-area.model';
import { getEntities as getEnumEmpTaxAreas } from 'app/entities/enum-emp-tax-area/enum-emp-tax-area.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { getEntity, updateEntity, createEntity, reset } from './social-security-benefits.reducer';
import { ISocialSecurityBenefits } from 'app/shared/model/social-security-benefits.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISocialSecurityBenefitsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISocialSecurityBenefitsUpdateState {
  isNew: boolean;
  pfTypeId: string;
  pfStatusId: string;
  providentPaySchemeId: string;
  socialSecurityPaySchemeId: string;
  ssStatusId: string;
  laborTypeId: string;
  taxerTypeId: string;
  taxAreaId: string;
  empId: string;
}

export class SocialSecurityBenefitsUpdate extends React.Component<ISocialSecurityBenefitsUpdateProps, ISocialSecurityBenefitsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      pfTypeId: '0',
      pfStatusId: '0',
      providentPaySchemeId: '0',
      socialSecurityPaySchemeId: '0',
      ssStatusId: '0',
      laborTypeId: '0',
      taxerTypeId: '0',
      taxAreaId: '0',
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

    this.props.getEnumPfTypes();
    this.props.getEnumPfStatuses();
    this.props.getEnumPfPaySchemes();
    this.props.getEnumSsPaySchemes();
    this.props.getEnumSsStatuses();
    this.props.getEnumEmpLaborTypes();
    this.props.getEnumEmpTaxerTypes();
    this.props.getEnumEmpTaxAreas();
    this.props.getEmployees();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { socialSecurityBenefitsEntity } = this.props;
      const entity = {
        ...socialSecurityBenefitsEntity,
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
    this.props.history.push('/entity/social-security-benefits');
  };

  render() {
    const {
      socialSecurityBenefitsEntity,
      enumPfTypes,
      enumPfStatuses,
      enumPfPaySchemes,
      enumSsPaySchemes,
      enumSsStatuses,
      enumEmpLaborTypes,
      enumEmpTaxerTypes,
      enumEmpTaxAreas,
      employees,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="rosterServer3App.socialSecurityBenefits.home.createOrEditLabel">
              <Translate contentKey="rosterServer3App.socialSecurityBenefits.home.createOrEditLabel">
                Create or edit a SocialSecurityBenefits
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : socialSecurityBenefitsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="social-security-benefits-id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="social-security-benefits-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codeLabel" for="social-security-benefits-code">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.code">Code</Translate>
                  </Label>
                  <AvField id="social-security-benefits-code" type="text" name="code" />
                  <UncontrolledTooltip target="codeLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.code" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="pfAccountLabel" for="social-security-benefits-pfAccount">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfAccount">Pf Account</Translate>
                  </Label>
                  <AvField id="social-security-benefits-pfAccount" type="text" name="pfAccount" />
                  <UncontrolledTooltip target="pfAccountLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfAccount" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="spfAccountLabel" for="social-security-benefits-spfAccount">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.spfAccount">Spf Account</Translate>
                  </Label>
                  <AvField id="social-security-benefits-spfAccount" type="text" name="spfAccount" />
                  <UncontrolledTooltip target="spfAccountLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.spfAccount" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="pfStartMonthLabel" for="social-security-benefits-pfStartMonth">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStartMonth">Pf Start Month</Translate>
                  </Label>
                  <AvField id="social-security-benefits-pfStartMonth" type="date" className="form-control" name="pfStartMonth" />
                  <UncontrolledTooltip target="pfStartMonthLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfStartMonth" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="pfBaseLabel" for="social-security-benefits-pfBase">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfBase">Pf Base</Translate>
                  </Label>
                  <AvField id="social-security-benefits-pfBase" type="string" className="form-control" name="pfBase" />
                  <UncontrolledTooltip target="pfBaseLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfBase" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="pfStopMonthLabel" for="social-security-benefits-pfStopMonth">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStopMonth">Pf Stop Month</Translate>
                  </Label>
                  <AvField id="social-security-benefits-pfStopMonth" type="date" className="form-control" name="pfStopMonth" />
                  <UncontrolledTooltip target="pfStopMonthLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfStopMonth" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="pfRemarkLabel" for="social-security-benefits-pfRemark">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfRemark">Pf Remark</Translate>
                  </Label>
                  <AvField id="social-security-benefits-pfRemark" type="text" name="pfRemark" />
                  <UncontrolledTooltip target="pfRemarkLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.pfRemark" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssAccountLabel" for="social-security-benefits-ssAccount">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssAccount">Ss Account</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssAccount" type="text" name="ssAccount" />
                  <UncontrolledTooltip target="ssAccountLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssAccount" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssCityLabel" for="social-security-benefits-ssCity">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssCity">Ss City</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssCity" type="text" name="ssCity" />
                  <UncontrolledTooltip target="ssCityLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssCity" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssStartMonthLabel" for="social-security-benefits-ssStartMonth">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStartMonth">Ss Start Month</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssStartMonth" type="date" className="form-control" name="ssStartMonth" />
                  <UncontrolledTooltip target="ssStartMonthLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssStartMonth" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssBaseLabel" for="social-security-benefits-ssBase">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssBase">Ss Base</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssBase" type="string" className="form-control" name="ssBase" />
                  <UncontrolledTooltip target="ssBaseLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssBase" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssStopMonthLabel" for="social-security-benefits-ssStopMonth">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStopMonth">Ss Stop Month</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssStopMonth" type="date" className="form-control" name="ssStopMonth" />
                  <UncontrolledTooltip target="ssStopMonthLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssStopMonth" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="ssRemarkLabel" for="social-security-benefits-ssRemark">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssRemark">Ss Remark</Translate>
                  </Label>
                  <AvField id="social-security-benefits-ssRemark" type="text" name="ssRemark" />
                  <UncontrolledTooltip target="ssRemarkLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.ssRemark" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="allowanceLabel" for="social-security-benefits-allowance">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.allowance">Allowance</Translate>
                  </Label>
                  <AvField id="social-security-benefits-allowance" type="text" name="allowance" />
                  <UncontrolledTooltip target="allowanceLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.allowance" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="taxpayerLabel" for="social-security-benefits-taxpayer">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxpayer">Taxpayer</Translate>
                  </Label>
                  <AvField id="social-security-benefits-taxpayer" type="text" name="taxpayer" />
                  <UncontrolledTooltip target="taxpayerLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.taxpayer" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isSelfVerifyLabel" check>
                    <AvInput id="social-security-benefits-isSelfVerify" type="checkbox" className="form-control" name="isSelfVerify" />
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.isSelfVerify">Is Self Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isSelfVerifyLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.isSelfVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label id="isHrVerifyLabel" check>
                    <AvInput id="social-security-benefits-isHrVerify" type="checkbox" className="form-control" name="isHrVerify" />
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.isHrVerify">Is Hr Verify</Translate>
                  </Label>
                  <UncontrolledTooltip target="isHrVerifyLabel">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.help.isHrVerify" />
                  </UncontrolledTooltip>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-pfType">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfType">Pf Type</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-pfType" type="select" className="form-control" name="pfTypeId">
                    <option value="" key="0" />
                    {enumPfTypes
                      ? enumPfTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-pfStatus">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.pfStatus">Pf Status</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-pfStatus" type="select" className="form-control" name="pfStatusId">
                    <option value="" key="0" />
                    {enumPfStatuses
                      ? enumPfStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-providentPayScheme">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.providentPayScheme">Provident Pay Scheme</Translate>
                  </Label>
                  <AvInput
                    id="social-security-benefits-providentPayScheme"
                    type="select"
                    className="form-control"
                    name="providentPaySchemeId"
                  >
                    <option value="" key="0" />
                    {enumPfPaySchemes
                      ? enumPfPaySchemes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-socialSecurityPayScheme">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.socialSecurityPayScheme">
                      Social Security Pay Scheme
                    </Translate>
                  </Label>
                  <AvInput
                    id="social-security-benefits-socialSecurityPayScheme"
                    type="select"
                    className="form-control"
                    name="socialSecurityPaySchemeId"
                  >
                    <option value="" key="0" />
                    {enumSsPaySchemes
                      ? enumSsPaySchemes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-ssStatus">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.ssStatus">Ss Status</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-ssStatus" type="select" className="form-control" name="ssStatusId">
                    <option value="" key="0" />
                    {enumSsStatuses
                      ? enumSsStatuses.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-laborType">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.laborType">Labor Type</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-laborType" type="select" className="form-control" name="laborTypeId">
                    <option value="" key="0" />
                    {enumEmpLaborTypes
                      ? enumEmpLaborTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-taxerType">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxerType">Taxer Type</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-taxerType" type="select" className="form-control" name="taxerTypeId">
                    <option value="" key="0" />
                    {enumEmpTaxerTypes
                      ? enumEmpTaxerTypes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-taxArea">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.taxArea">Tax Area</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-taxArea" type="select" className="form-control" name="taxAreaId">
                    <option value="" key="0" />
                    {enumEmpTaxAreas
                      ? enumEmpTaxAreas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="social-security-benefits-emp">
                    <Translate contentKey="rosterServer3App.socialSecurityBenefits.emp">Emp</Translate>
                  </Label>
                  <AvInput id="social-security-benefits-emp" type="select" className="form-control" name="empId">
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
                <Button tag={Link} id="cancel-save" to="/entity/social-security-benefits" replace color="info">
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
  enumPfTypes: storeState.enumPfType.entities,
  enumPfStatuses: storeState.enumPfStatus.entities,
  enumPfPaySchemes: storeState.enumPfPayScheme.entities,
  enumSsPaySchemes: storeState.enumSsPayScheme.entities,
  enumSsStatuses: storeState.enumSsStatus.entities,
  enumEmpLaborTypes: storeState.enumEmpLaborType.entities,
  enumEmpTaxerTypes: storeState.enumEmpTaxerType.entities,
  enumEmpTaxAreas: storeState.enumEmpTaxArea.entities,
  employees: storeState.employee.entities,
  socialSecurityBenefitsEntity: storeState.socialSecurityBenefits.entity,
  loading: storeState.socialSecurityBenefits.loading,
  updating: storeState.socialSecurityBenefits.updating,
  updateSuccess: storeState.socialSecurityBenefits.updateSuccess
});

const mapDispatchToProps = {
  getEnumPfTypes,
  getEnumPfStatuses,
  getEnumPfPaySchemes,
  getEnumSsPaySchemes,
  getEnumSsStatuses,
  getEnumEmpLaborTypes,
  getEnumEmpTaxerTypes,
  getEnumEmpTaxAreas,
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
)(SocialSecurityBenefitsUpdate);
