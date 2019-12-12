import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IngredientsComponent} from './ingredients/ingredients.component';


const routes: Routes = [
  {path: 'ingredients', component: IngredientsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
