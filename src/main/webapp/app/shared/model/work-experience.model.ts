import { Moment } from 'moment';

export interface IWorkExperience {
  id?: number;
  code?: string;
  eName?: string;
  phoneNum?: string;
  company?: string;
  job?: string;
  jobDesc?: string;
  hireDate?: Moment;
  leaveDate?: Moment;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  empId?: number;
}

export const defaultValue: Readonly<IWorkExperience> = {
  isSelfVerify: false,
  isHrVerify: false
};
