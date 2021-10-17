import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../customer.service';
import {Router} from '@angular/router';
import {Customer} from '../../model/customer';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  length: number;
  customers: Customer[];
  nameDelete: string;
  idDelete: number;
  page = 1;
  pageSize = 4;
  nameSearch = '';
  dateOfBirthTo = '';
  dateOfBirthFrom = '';
  count = 0;
  message = '';
  limit= 2;

  constructor(private customerService: CustomerService,
              private router: Router) {

  }

  ngOnInit(): void {

    this.loadList();
  }

  deleteById(id: number) {
    this.customerService.deleteById(Number(id)).subscribe(value => {

    }, error => {

    }, () => {
      this.loadList();

    });
  }

  passData(idFromView: number, nameFromView: string) {
    this.nameDelete = nameFromView;
    this.idDelete = idFromView;
  }


  loadList() {
    this.customerService.findAll().subscribe((data) => {
      this.customers = data;
      console.log(data);
      this.length = data.length;
    });
  }

  search() {
    this.customerService.findAll().subscribe((data) => {
      let customersSearch: Customer[] = [];
      let dobCus;
      let dobTo = new Date(this.dateOfBirthTo);
      let dobFrom = new Date(this.dateOfBirthFrom);
      for (let i = 0; i < data.length; i++) {
        dobCus = new Date(data[i].customerBirthday);
        if ((data[i].customerName).indexOf(this.nameSearch) > -1) {
          if ((+dobCus >= +dobTo && +dobCus <= +dobFrom)
            || (this.dateOfBirthTo == '' && +dobCus <= +dobFrom)
            || (this.dateOfBirthFrom == '' && +dobCus >= +dobTo)
            || (this.dateOfBirthFrom == '' && this.dateOfBirthTo == '')) {
            customersSearch.push(data[i]);
          }
        }

      }
      this.customers = customersSearch;
      if(this.customers.length === 0){
        this.message = 'khong co customer nao phu hop voi dieu kien search.'
      } else {
        this.message = '';
      }
      this.length = this.customers.length;
    });
  }

  setNameSearch($event: any) {
    this.nameSearch = $event.target.value;
  }

  setDOBTo($event: any) {
    this.dateOfBirthTo = $event.target.value;
    console.log(this.dateOfBirthTo);
  }

  setDOBFrom($event: any) {
    this.dateOfBirthFrom = $event.target.value;
    console.log(this.dateOfBirthFrom);
  }

  sortId(){
    this.count += 1;
    this.customerService.sortId(this.count).subscribe(value => {
      this.customers = value;
    })
    // this.count += 1;
    // if (this.count%2 == 0){
    //   this.customers = this.customers.sort(function(a, b) {
    //     return a.id - b.id;
    //   });
    // }else {
    //   this.customers = this.customers.sort(function(a, b) {
    //     return b.id - a.id;
    //   });
    // }

  }

}
