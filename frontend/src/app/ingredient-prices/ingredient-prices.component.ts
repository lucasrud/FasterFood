import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../ingredient';

@Component({
  selector: 'app-ingredient-prices',
  templateUrl: './ingredient-prices.component.html',
  styleUrls: ['./ingredient-prices.component.css']
})
export class IngredientPricesComponent implements OnInit {
  ingredients: Ingredient[];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<Ingredient[]>('/api/price/ingredients').subscribe( ingredients => this.ingredients = ingredients);
  }
  changePrice(ingredient, price) {
    ingredient.purchasePrice = price;
    this.http.post<Ingredient[]>('/api/price/ingredients', ingredient).subscribe( ingredients => this.ingredients = ingredients);
  }

}
