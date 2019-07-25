export interface IPayCard {
  id?: number;
  code?: string;
  branch?: string;
  accountName?: string;
  bankAccount?: string;
  depositBank?: string;
  isSelfVerify?: boolean;
  isHrVerify?: boolean;
  empId?: number;
}

export const defaultValue: Readonly<IPayCard> = {
  isSelfVerify: false,
  isHrVerify: false
};
