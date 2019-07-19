export const enum BloodType {
  A = 'A',
  B = 'B',
  O = 'O',
  AB = 'AB'
}

export interface IPersonal {
  id?: number;
  code?: string;
  stageName?: string;
  idName?: string;
  nation?: string;
  accountLoc?: string;
  nativePlace?: string;
  currentAddr?: string;
  spouseName?: string;
  childName?: string;
  bloodType?: BloodType;
  emergencyContactName?: string;
  emergencyContactPhone?: string;
  qq?: string;
  wechat?: string;
  personalEmail?: string;
  remark?: string;
  others?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  accountTypeId?: number;
  highestEducationId?: number;
  politicsStatusId?: number;
  maritalStatusId?: number;
  empId?: number;
}

export const defaultValue: Readonly<IPersonal> = {
  isSelfVerify: false,
  isHrVerify: false
};
