import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Ingredient} from '../ingredient';
import {ingredientDTO} from '../ingredientDTO';
import {Meal} from '../meal';


@Component({
  selector: 'app-ingredient-prices',
  templateUrl: './ingredients.component.html',
  styleUrls: ['./ingredients.component.css']
})

export class IngredientsComponent implements OnInit {

  ingredients: Ingredient[];
  dependentMeals: Meal[] = [];
  dependentMealsInformation = '';


  constructor(private http: HttpClient) {
    this.http.get<Ingredient[]>('/api/ingredients').subscribe( ingredients => this.ingredients = ingredients);
  }

  ngOnInit() {  }

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

  addIngredient(iname, iprice, istock) {
    if (!isNaN(Number(iprice)) && !(iprice === '')) {
      const m: ingredientDTO = {
        name: iname,
        purchasePrice: iprice,
        stock: istock
      };
      this.http.post<Ingredient[]>('/api/ingredients/add', m).subscribe(ingredients => this.ingredients = ingredients);
    }
  }

  deleteIngredient(ingredient) {

    this.dependentMealsInformation = '';
    this.http.post<Meal[]>('/api/ingredients/checkdependencies', ingredient).subscribe(deps => this.dependentMeals = deps);
    alert(this.dependentMeals.length);  // Wenn diese Zeile gelöscht wird, dann funktioniert diese ganze Methode nicht, es ist verrückt!!

    if (this.dependentMeals.length > 0) {
      this.dependentMealsInformation = 'Deletion not possible, ' + ingredient.name + ' is still in use for these Meals: ';
      this.dependentMeals.forEach(dep => this.dependentMealsInformation += ' ' + dep.name);
      this.dependentMeals = [];
    } else {
      this.http.post<Ingredient[]>('/api/ingredients/delete', ingredient).subscribe(ingredients => this.ingredients = ingredients);
      this.dependentMeals = [];
    }
  }
}
