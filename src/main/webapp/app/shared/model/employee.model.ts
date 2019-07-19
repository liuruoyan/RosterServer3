import { Moment } from 'moment';
import { IContract } from 'app/shared/model/contract.model';
import { IPersonal } from 'app/shared/model/personal.model';
import { ISocialSecurityBenefits } from 'app/shared/model/social-security-benefits.model';
import { IPayCard } from 'app/shared/model/pay-card.model';
import { IDimission } from 'app/shared/model/dimission.model';
import { IWorkExperience } from 'app/shared/model/work-experience.model';
import { IEducationExperience } from 'app/shared/model/education-experience.model';
import { IDirectSupervisor } from 'app/shared/model/direct-supervisor.model';
import { IAdditionalPost } from 'app/shared/model/additional-post.model';

export const enum BirthType {
  LUNAR = 'LUNAR',
  CALENDAR = 'CALENDAR'
}

export interface IEmployee {
  id?: number;
  code?: string;
  name?: string;
  idNumber?: string;
  phone?: string;
  hireDate?: Moment;
  jobGrade?: string;
  position?: string;
  job?: string;
  deptName?: string;
  empNo?: string;
  seniority?: number;
  contractor?: string;
  birthType?: BirthType;
  birthday?: Moment;
  workLoc?: string;
  contactAddr?: string;
  nationality?: string;
  firstName?: string;
  lastName?: string;
  others?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  contracts?: IContract[];
  personals?: IPersonal[];
  socialSecurityBenefits?: ISocialSecurityBenefits[];
  payCards?: IPayCard[];
  dimissions?: IDimission[];
  workExperiences?: IWorkExperience[];
  educationExperiences?: IEducationExperience[];
  directSupervisors?: IDirectSupervisor[];
  additionalPosts?: IAdditionalPost[];
  statusId?: number;
  idTypeId?: number;
  contractTypeId?: number;
  empTypeId?: number;
  genderId?: number;
}

export const defaultValue: Readonly<IEmployee> = {
  isSelfVerify: false,
  isHrVerify: false
};
