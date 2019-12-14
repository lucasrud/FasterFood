import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../ingredient';
import {ingredientDTO} from '../ingredientDTO';
import {Meal} from '../meal';
import {StringDTO} from '../stringDTO';


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

    // let dependencies: StringDTO[] = [];
    // this.http.post<StringDTO[]>('"/api/ingredients/checkdependencies', ingredient).subscribe(deps => dependencies = deps);
    // dependencies.forEach(dep => console.log(dep));

    this.http.post<Ingredient[]>('/api/ingredients/delete', ingredient).subscribe(ingredients => this.ingredients = ingredients);
  }
}
