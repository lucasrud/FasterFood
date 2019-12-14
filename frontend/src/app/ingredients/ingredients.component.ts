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
  dependentMeals: Meal[];

  constructor(private http: HttpClient) {
    this.http.get<Ingredient[]>('/api/ingredients').subscribe( ingredients => this.ingredients = ingredients);
  }

  ngOnInit() {
    this.dependentMeals = [{id: 0, name: '', purchasePrice: 0, retailPrice: 0, profit: 0, ingredients: []}];
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

    this.http.post<Meal[]>('/api/ingredients/checkdependencies', ingredient).subscribe(deps => this.dependentMeals = deps);
    alert(this.dependentMeals.length);  // Wenn diese Zeile gelöscht wird, dann funktioniert diese ganze Methode nicht, es ist verrückt!!

    
    // Muss auch noch getestet werden, wenn in die dependentMeals nur eine dependency gefunden wird, zur Sicherheit, es scheint, dass
    // das TS array.length anders funktioniert?
    if (this.dependentMeals.length > 0) {
      let text = 'Deletion not possible, ingredients are still in use for these Meals: ';
      this.dependentMeals.forEach(dep => text += ' ' + dep.name);
      alert(text);
      this.dependentMeals = [{id: 0, name: '', purchasePrice: 0, retailPrice: 0, profit: 0, ingredients: []}];
    } else {
      this.http.post<Ingredient[]>('/api/ingredients/delete', ingredient).subscribe(ingredients => this.ingredients = ingredients);
      this.dependentMeals = [{id: 0, name: '', purchasePrice: 0, retailPrice: 0, profit: 0, ingredients: []}];
    }
  }
}
