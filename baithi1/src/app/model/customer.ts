import {customerType} from './customerType';

export interface Customer {
  id: number;
  customerId?: string;
  customerName: string;
  customerBirthday: string;
  idCard: string;
  phone: string;
  customerEmail: string;
  address: string;
  customerType: customerType[];
}
