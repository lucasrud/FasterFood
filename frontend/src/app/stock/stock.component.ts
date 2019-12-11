import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../ingredient';
import {IngredientService} from '../ingredient.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  ingredients: Ingredient[];
  ingredientService: IngredientService;

  constructor(private http: HttpClient, ingredientService: IngredientService) {
    this.ingredientService = ingredientService;
  }

  ngOnInit() {
    this.http.get<Ingredient[]>('/api/stock/ingredients').subscribe( ingredients => this.ingredients = ingredients);
  }

  changeStockForIngredient(ingredient, stock) {

    console.log(ingredient.stock);
    ingredient.stock = stock;
    console.log(ingredient.retailPrice);
    console.log(ingredient.stock);
    this.http.post<Ingredient[]>('/api/stock/ingredients', ingredient).subscribe( ing => this.ingredients = ing);
  }



}
