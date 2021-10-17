import {Injectable} from '@angular/core';
import {Customer} from '../model/customer';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {customerType} from '../model/customerType';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  // typeCustomers: TypeCustomer[];
  // customers: Customer[];

  URL: string = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.URL);
  }

  save(customer: Customer): Observable<void> {
    return this.http.post<void>(this.URL, customer);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(this.URL + '/' + id);
  }

  findById(id: number): Observable<Customer>{
    return this.http.get<Customer>(this.URL + '/'+ id);

  }
  update(customer: Customer): Observable<void> {
    // this.findAllTypeCustomer().subscribe(value => {
    //   this.typeCustomers = value;
    //   let typeCustomerCreate = customer.typeCustomer.name;
    //   for (let i = 0; i < this.typeCustomers.length; i++) {
    //     if (this.typeCustomers[i].name == typeCustomerCreate) {
    //       customer.typeCustomer = this.typeCustomers[i];
    //       break;
    //     }
    //   }
    // });
    return this.http.put<void>(this.URL + '/' + customer.id, customer);
  }

  findAllTypeCustomer(): Observable<customerType[]> {
    return this.http.get<customerType[]>('http://localhost:8080/customer');
  }

  sortId(count: number): Observable<Customer[]>{
    if (count%2==0){
      return this.http.get<Customer[]>('http://localhost:3000/customerList?_sort=id&_order=desc');

    }else {
      return this.http.get<Customer[]>('http://localhost:3000/customerList?_sort=id&_order=asc');
    }
  }

  // paginate(count: number): Observable<Customer[]>{
  //   return this.http.get<Customer[]>('http://localhost:3000/customerList?_page='+ count +'&_limit=2');
  // }

}
