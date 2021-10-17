import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../customer.service';
import {Customer} from '../../model/customer';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TypeCustomer} from '../../model/type-customer';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {

  customer: Customer;
  private id: number;
  customerFormGroup: FormGroup;
  typeCustomers: TypeCustomer[];
  selectedValue: TypeCustomer;


  constructor(private customerService: CustomerService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastService: ToastrService) {
  }

  ngOnInit(): void {
    this.customerService.findAllTypeCustomer().subscribe(value => {
      this.typeCustomers = value;
    });

    this.id = Number(this.activatedRoute.snapshot.params.id);

    this.customerService.findById(this.id).subscribe(value => {
      this.customer = value;
      // this.selectedValue = this.customer.typeCustomer;
      this.customerFormGroup = new FormGroup({
        id: new FormControl(this.customer.id),
        idCustomer: new FormControl(this.customer.idCustomer),
        name: new FormControl(this.customer.name),
        idCard: new FormControl(this.customer.idCard),
        dateOfBirth: new FormControl(this.customer.dateOfBirth),
        phone: new FormControl(this.customer.phone),
        email: new FormControl(this.customer.email),
        address: new FormControl(this.customer.address),
        typeCustomer: new FormControl(this.customer.typeCustomer),
      });

    }, error => {

    },);

  }

  updateCustomer(): void {
    let customerObj: Customer = Object.assign({}, this.customerFormGroup.value);
    this.customerService.update(customerObj).subscribe(value => {
      this.callToastr();
    }, error => {

    }, () => {
      this.router.navigateByUrl('/list');
    });
  }

  customCompare(o1: TypeCustomer, o2:TypeCustomer){
      return o1.id == o2.id ;
  }
  callToastr(){
    this.toastService.success("success.....","update", {
      timeOut : 1000,
      progressBar: true,
      progressAnimation: 'increasing'
    })
  }
}
