import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {MealsComponent} from './meals/meals.component';
import {CashregisterComponent} from './cashregister/cashregister.component';


const routes: Routes = [
  { path: '', component: CashregisterComponent },
  { path: 'edit-meals', component: MealsComponent }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
