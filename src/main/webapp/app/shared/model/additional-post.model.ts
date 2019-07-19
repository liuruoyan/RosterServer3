import { Moment } from 'moment';

export interface IAdditionalPost {
  id?: number;
  code?: string;
  name?: string;
  phone?: string;
  dept?: string;
  job?: string;
  startDate?: Moment;
  endDate?: Moment;
  remark?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  empId?: number;
}

export const defaultValue: Readonly<IAdditionalPost> = {
  isSelfVerify: false,
  isHrVerify: false
};
