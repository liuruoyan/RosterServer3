import { Moment } from 'moment';

export interface ISocialSecurityBenefits {
  id?: number;
  code?: string;
  pfAccount?: string;
  spfAccount?: string;
  pfStartMonth?: Moment;
  pfBase?: number;
  pfStopMonth?: Moment;
  pfRemark?: string;
  ssAccount?: string;
  ssCity?: string;
  ssStartMonth?: Moment;
  ssBase?: number;
  ssStopMonth?: Moment;
  ssRemark?: string;
  allowance?: number;
  taxpayer?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  pfTypeId?: number;
  pfStatusId?: number;
  providentPaySchemeId?: number;
  socialSecurityPaySchemeId?: number;
  ssStatusId?: number;
  laborTypeId?: number;
  taxerTypeId?: number;
  taxAreaId?: number;
  empId?: number;
}

export const defaultValue: Readonly<ISocialSecurityBenefits> = {
  isSelfVerify: false,
  isHrVerify: false
};
