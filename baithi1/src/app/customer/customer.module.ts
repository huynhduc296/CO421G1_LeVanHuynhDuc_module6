import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerDeleteComponent } from './customer-delete/customer-delete.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CustomerRoutingModule} from './customer-routing.module';
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';



@NgModule({
    declarations: [CustomerCreateComponent, CustomerUpdateComponent, CustomerListComponent, CustomerDeleteComponent],
  exports: [
    CustomerCreateComponent,
    CustomerListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CustomerRoutingModule,
    RouterModule,
    HttpClientModule,
    NgbPaginationModule,
    FormsModule
  ]
})
export class CustomerModule { }
