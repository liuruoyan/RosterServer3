import { Moment } from 'moment';

export interface IContract {
  id?: number;
  code?: string;
  startDate?: Moment;
  endDate?: Moment;
  email?: string;
  workTel?: string;
  probationEndDay?: Moment;
  probationLength?: number;
  other?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  contractTypeId?: number;
  empId?: number;
}

export const defaultValue: Readonly<IContract> = {
  isSelfVerify: false,
  isHrVerify: false
};
