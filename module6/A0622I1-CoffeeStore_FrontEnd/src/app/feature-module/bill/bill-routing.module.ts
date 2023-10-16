import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BillListComponent} from './bill-list/bill-list.component';


const routes: Routes = [
  {
    path: 'bill', component: BillListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BillRoutingModule { }
