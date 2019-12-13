import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { OrderComponent } from './order/order.component';
import { HttpClientModule } from '@angular/common/http';
import { IngredientsComponent } from './ingredients/ingredients.component';
import { MealsComponent } from './editMeals/meals.component';
import { CashregisterComponent } from './cashregister/cashregister.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderComponent,
    IngredientsComponent,
    MealsComponent,
    CashregisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
