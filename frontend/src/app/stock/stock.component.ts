import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
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
    this.http.get<Ingredient[]>('/api/stock/ingredients').subscribe( meals => this.ingredients = meals);
  }

  changeStockForIngredient(ingredient, stock) {

    ingredient.stock = stock;
    this.http.post<Ingredient[]>('/api/stock/ingredients', ingredient).subscribe( meals => this.ingredients = meals);
  }
}
