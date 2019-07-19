export interface IDirectSupervisor {
  id?: number;
  code?: string;
  name?: string;
  phone?: string;
  aSupName?: string;
  aSupPhone?: string;
  bSupName?: string;
  bSupPhone?: string;
  fSubName?: string;
  fSubPhone?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  empId?: number;
}

export const defaultValue: Readonly<IDirectSupervisor> = {
  isSelfVerify: false,
  isHrVerify: false
};
