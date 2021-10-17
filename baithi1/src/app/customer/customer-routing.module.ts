import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {CustomerCreateComponent} from './customer-create/customer-create.component';
import {CustomerUpdateComponent} from './customer-update/customer-update.component';

const routes: Routes = [
  {
    path: 'list',
    component: CustomerListComponent
  },
  {
    path: 'create',
    component: CustomerCreateComponent
  },
  {
    path: 'update/:id',
    component: CustomerUpdateComponent
  },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ]
})
export class CustomerRoutingModule { }
