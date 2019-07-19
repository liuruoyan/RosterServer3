import { Moment } from 'moment';

export interface IDimission {
  id?: number;
  code?: string;
  lastDate?: Moment;
  reason?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  dimissionTypeId?: number;
  empId?: number;
}

export const defaultValue: Readonly<IDimission> = {
  isSelfVerify: false,
  isHrVerify: false
};
