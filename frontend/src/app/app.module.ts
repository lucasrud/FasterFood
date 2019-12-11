import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { OrderComponent } from './order/order.component';
import { HttpClientModule } from '@angular/common/http';
import { StockComponent } from './stock/stock.component';
import { PricesComponent } from './prices/prices.component';
import { IngredientPricesComponent } from './ingredient-prices/ingredient-prices.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderComponent,
    StockComponent,
    PricesComponent,
    IngredientPricesComponent
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
