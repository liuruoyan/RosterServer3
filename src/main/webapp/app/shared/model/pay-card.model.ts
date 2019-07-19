export interface IPayCard {
  id?: number;
  code?: string;
  branch?: string;
  accountName?: string;
  bankAccount?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  depositBankId?: number;
  empId?: number;
}

export const defaultValue: Readonly<IPayCard> = {
  isSelfVerify: false,
  isHrVerify: false
};
