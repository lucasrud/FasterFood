import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IngredientsComponent} from './ingredients/ingredients.component';
import {MealsComponent} from './editMeals/meals.component';
import {CashregisterComponent} from './cashregister/cashregister.component';
import {FinanceComponent} from './finance/finance.component';
import {RegisterComponent} from './register/register.component';


const routes: Routes = [
  { path: '', component: CashregisterComponent },
  { path: 'ingredients', component: IngredientsComponent},
  { path: 'edit-meals', component: MealsComponent },
  { path: 'finance', component: FinanceComponent },
  { path: 'register', component: RegisterComponent },
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
