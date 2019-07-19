import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import employee, {
  EmployeeState
} from 'app/entities/employee/employee.reducer';
// prettier-ignore
import enumEmpStatus, {
  EnumEmpStatusState
} from 'app/entities/enum-emp-status/enum-emp-status.reducer';
// prettier-ignore
import enumIdType, {
  EnumIdTypeState
} from 'app/entities/enum-id-type/enum-id-type.reducer';
// prettier-ignore
import enumContractType, {
  EnumContractTypeState
} from 'app/entities/enum-contract-type/enum-contract-type.reducer';
// prettier-ignore
import enumEmpType, {
  EnumEmpTypeState
} from 'app/entities/enum-emp-type/enum-emp-type.reducer';
// prettier-ignore
import enumGender, {
  EnumGenderState
} from 'app/entities/enum-gender/enum-gender.reducer';
// prettier-ignore
import contract, {
  ContractState
} from 'app/entities/contract/contract.reducer';
// prettier-ignore
import personal, {
  PersonalState
} from 'app/entities/personal/personal.reducer';
// prettier-ignore
import enumAccountType, {
  EnumAccountTypeState
} from 'app/entities/enum-account-type/enum-account-type.reducer';
// prettier-ignore
import enumHighestEducation, {
  EnumHighestEducationState
} from 'app/entities/enum-highest-education/enum-highest-education.reducer';
// prettier-ignore
import enumPoliticsStatus, {
  EnumPoliticsStatusState
} from 'app/entities/enum-politics-status/enum-politics-status.reducer';
// prettier-ignore
import enumMaritalStatus, {
  EnumMaritalStatusState
} from 'app/entities/enum-marital-status/enum-marital-status.reducer';
// prettier-ignore
import socialSecurityBenefits, {
  SocialSecurityBenefitsState
} from 'app/entities/social-security-benefits/social-security-benefits.reducer';
// prettier-ignore
import enumPfType, {
  EnumPfTypeState
} from 'app/entities/enum-pf-type/enum-pf-type.reducer';
// prettier-ignore
import enumPfStatus, {
  EnumPfStatusState
} from 'app/entities/enum-pf-status/enum-pf-status.reducer';
// prettier-ignore
import enumPfPayScheme, {
  EnumPfPaySchemeState
} from 'app/entities/enum-pf-pay-scheme/enum-pf-pay-scheme.reducer';
// prettier-ignore
import enumSsPayScheme, {
  EnumSsPaySchemeState
} from 'app/entities/enum-ss-pay-scheme/enum-ss-pay-scheme.reducer';
// prettier-ignore
import enumSsStatus, {
  EnumSsStatusState
} from 'app/entities/enum-ss-status/enum-ss-status.reducer';
// prettier-ignore
import enumEmpLaborType, {
  EnumEmpLaborTypeState
} from 'app/entities/enum-emp-labor-type/enum-emp-labor-type.reducer';
// prettier-ignore
import enumEmpTaxerType, {
  EnumEmpTaxerTypeState
} from 'app/entities/enum-emp-taxer-type/enum-emp-taxer-type.reducer';
// prettier-ignore
import enumEmpTaxArea, {
  EnumEmpTaxAreaState
} from 'app/entities/enum-emp-tax-area/enum-emp-tax-area.reducer';
// prettier-ignore
import payCard, {
  PayCardState
} from 'app/entities/pay-card/pay-card.reducer';
// prettier-ignore
import enumDepositBank, {
  EnumDepositBankState
} from 'app/entities/enum-deposit-bank/enum-deposit-bank.reducer';
// prettier-ignore
import dimission, {
  DimissionState
} from 'app/entities/dimission/dimission.reducer';
// prettier-ignore
import enumDimissionType, {
  EnumDimissionTypeState
} from 'app/entities/enum-dimission-type/enum-dimission-type.reducer';
// prettier-ignore
import workExperience, {
  WorkExperienceState
} from 'app/entities/work-experience/work-experience.reducer';
// prettier-ignore
import educationExperience, {
  EducationExperienceState
} from 'app/entities/education-experience/education-experience.reducer';
// prettier-ignore
import enumEducation, {
  EnumEducationState
} from 'app/entities/enum-education/enum-education.reducer';
// prettier-ignore
import directSupervisor, {
  DirectSupervisorState
} from 'app/entities/direct-supervisor/direct-supervisor.reducer';
// prettier-ignore
import additionalPost, {
  AdditionalPostState
} from 'app/entities/additional-post/additional-post.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly employee: EmployeeState;
  readonly enumEmpStatus: EnumEmpStatusState;
  readonly enumIdType: EnumIdTypeState;
  readonly enumContractType: EnumContractTypeState;
  readonly enumEmpType: EnumEmpTypeState;
  readonly enumGender: EnumGenderState;
  readonly contract: ContractState;
  readonly personal: PersonalState;
  readonly enumAccountType: EnumAccountTypeState;
  readonly enumHighestEducation: EnumHighestEducationState;
  readonly enumPoliticsStatus: EnumPoliticsStatusState;
  readonly enumMaritalStatus: EnumMaritalStatusState;
  readonly socialSecurityBenefits: SocialSecurityBenefitsState;
  readonly enumPfType: EnumPfTypeState;
  readonly enumPfStatus: EnumPfStatusState;
  readonly enumPfPayScheme: EnumPfPaySchemeState;
  readonly enumSsPayScheme: EnumSsPaySchemeState;
  readonly enumSsStatus: EnumSsStatusState;
  readonly enumEmpLaborType: EnumEmpLaborTypeState;
  readonly enumEmpTaxerType: EnumEmpTaxerTypeState;
  readonly enumEmpTaxArea: EnumEmpTaxAreaState;
  readonly payCard: PayCardState;
  readonly enumDepositBank: EnumDepositBankState;
  readonly dimission: DimissionState;
  readonly enumDimissionType: EnumDimissionTypeState;
  readonly workExperience: WorkExperienceState;
  readonly educationExperience: EducationExperienceState;
  readonly enumEducation: EnumEducationState;
  readonly directSupervisor: DirectSupervisorState;
  readonly additionalPost: AdditionalPostState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  employee,
  enumEmpStatus,
  enumIdType,
  enumContractType,
  enumEmpType,
  enumGender,
  contract,
  personal,
  enumAccountType,
  enumHighestEducation,
  enumPoliticsStatus,
  enumMaritalStatus,
  socialSecurityBenefits,
  enumPfType,
  enumPfStatus,
  enumPfPayScheme,
  enumSsPayScheme,
  enumSsStatus,
  enumEmpLaborType,
  enumEmpTaxerType,
  enumEmpTaxArea,
  payCard,
  enumDepositBank,
  dimission,
  enumDimissionType,
  workExperience,
  educationExperience,
  enumEducation,
  directSupervisor,
  additionalPost,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
