import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../ingredient';


@Component({
  selector: 'app-ingredient-prices',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})

export class IngredientsComponent implements OnInit {

  ingredients: Ingredient[];

  constructor(private http: HttpClient) { }


  ngOnInit() {
    this.http.get<Ingredient[]>('/api/ingredients').subscribe( ingredients => this.ingredients = ingredients);
  }

  changePriceForIngredient(ingredient, price) {
    if (!isNaN(Number(price)) && !(price === '')) {
      ingredient.purchasePrice = price;
      this.http.post<Ingredient[]>('/api/ingredients/price', ingredient).subscribe(ingredients => this.ingredients = ingredients);
    }
  }

  changeStockForIngredient(ingredient, stock) {
    if (!isNaN(Number(stock)) && !(stock === '')) {
      ingredient.stock = stock;
      this.http.post<Ingredient[]>('/api/ingredients/stock', ingredient).subscribe( ingredients => this.ingredients = ingredients);
    }
  }
}
