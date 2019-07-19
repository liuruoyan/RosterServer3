import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Employee from './employee';
import EnumEmpStatus from './enum-emp-status';
import EnumIdType from './enum-id-type';
import EnumContractType from './enum-contract-type';
import EnumEmpType from './enum-emp-type';
import EnumGender from './enum-gender';
import Contract from './contract';
import Personal from './personal';
import EnumAccountType from './enum-account-type';
import EnumHighestEducation from './enum-highest-education';
import EnumPoliticsStatus from './enum-politics-status';
import EnumMaritalStatus from './enum-marital-status';
import SocialSecurityBenefits from './social-security-benefits';
import EnumPfType from './enum-pf-type';
import EnumPfStatus from './enum-pf-status';
import EnumPfPayScheme from './enum-pf-pay-scheme';
import EnumSsPayScheme from './enum-ss-pay-scheme';
import EnumSsStatus from './enum-ss-status';
import EnumEmpLaborType from './enum-emp-labor-type';
import EnumEmpTaxerType from './enum-emp-taxer-type';
import EnumEmpTaxArea from './enum-emp-tax-area';
import PayCard from './pay-card';
import EnumDepositBank from './enum-deposit-bank';
import Dimission from './dimission';
import EnumDimissionType from './enum-dimission-type';
import WorkExperience from './work-experience';
import EducationExperience from './education-experience';
import EnumEducation from './enum-education';
import DirectSupervisor from './direct-supervisor';
import AdditionalPost from './additional-post';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      <ErrorBoundaryRoute path={`${match.url}/enum-emp-status`} component={EnumEmpStatus} />
      <ErrorBoundaryRoute path={`${match.url}/enum-id-type`} component={EnumIdType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-contract-type`} component={EnumContractType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-emp-type`} component={EnumEmpType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-gender`} component={EnumGender} />
      <ErrorBoundaryRoute path={`${match.url}/contract`} component={Contract} />
      <ErrorBoundaryRoute path={`${match.url}/personal`} component={Personal} />
      <ErrorBoundaryRoute path={`${match.url}/enum-account-type`} component={EnumAccountType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-highest-education`} component={EnumHighestEducation} />
      <ErrorBoundaryRoute path={`${match.url}/enum-politics-status`} component={EnumPoliticsStatus} />
      <ErrorBoundaryRoute path={`${match.url}/enum-marital-status`} component={EnumMaritalStatus} />
      <ErrorBoundaryRoute path={`${match.url}/social-security-benefits`} component={SocialSecurityBenefits} />
      <ErrorBoundaryRoute path={`${match.url}/enum-pf-type`} component={EnumPfType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-pf-status`} component={EnumPfStatus} />
      <ErrorBoundaryRoute path={`${match.url}/enum-pf-pay-scheme`} component={EnumPfPayScheme} />
      <ErrorBoundaryRoute path={`${match.url}/enum-ss-pay-scheme`} component={EnumSsPayScheme} />
      <ErrorBoundaryRoute path={`${match.url}/enum-ss-status`} component={EnumSsStatus} />
      <ErrorBoundaryRoute path={`${match.url}/enum-emp-labor-type`} component={EnumEmpLaborType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-emp-taxer-type`} component={EnumEmpTaxerType} />
      <ErrorBoundaryRoute path={`${match.url}/enum-emp-tax-area`} component={EnumEmpTaxArea} />
      <ErrorBoundaryRoute path={`${match.url}/pay-card`} component={PayCard} />
      <ErrorBoundaryRoute path={`${match.url}/enum-deposit-bank`} component={EnumDepositBank} />
      <ErrorBoundaryRoute path={`${match.url}/dimission`} component={Dimission} />
      <ErrorBoundaryRoute path={`${match.url}/enum-dimission-type`} component={EnumDimissionType} />
      <ErrorBoundaryRoute path={`${match.url}/work-experience`} component={WorkExperience} />
      <ErrorBoundaryRoute path={`${match.url}/education-experience`} component={EducationExperience} />
      <ErrorBoundaryRoute path={`${match.url}/enum-education`} component={EnumEducation} />
      <ErrorBoundaryRoute path={`${match.url}/direct-supervisor`} component={DirectSupervisor} />
      <ErrorBoundaryRoute path={`${match.url}/additional-post`} component={AdditionalPost} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
