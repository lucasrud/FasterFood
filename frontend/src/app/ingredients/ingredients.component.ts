import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ingredient} from '../ingredient';
import {IngredientDTO} from '../ingredientDTO';
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
    this.http.get<Ingredient[]>('/api/ingredients').subscribe(ingredients => this.ingredients = ingredients);
  }

  ngOnInit() {
  }

  validateNumber(checknumber) {
    return (!isNaN(Number(checknumber)) && !(checknumber === '') && !(checknumber < 0));
  }

  changePriceForIngredient(ingredient, price) {
    if (this.validateNumber(price)) {
      ingredient.purchasePrice = price;
      this.http.post<Ingredient[]>('/api/ingredients/price', ingredient).subscribe(ingredients => this.ingredients = ingredients);
    }
  }

  changeStockForIngredient(ingredient, stock) {
    if (this.validateNumber(stock)) {
      ingredient.stock = stock;
      this.http.post<Ingredient[]>('/api/ingredients/changestock', ingredient).subscribe(ingredients => this.ingredients = ingredients);
    }
  }

  addIngredient(iname, iprice, istock) {
    if (this.validateNumber(iprice) && this.validateNumber(istock)) {
      const m: IngredientDTO = {
        name: iname,
        purchasePrice: iprice,
        stock: istock
      };
      this.http.post<Ingredient[]>('/api/ingredients/add', m).subscribe(ingredients => this.ingredients = ingredients);
    }
  }

  deleteIngredient(ingredient) {

    this.dependentMealsInformation = '';
    this.http.post<Meal[]>('/api/ingredients/mealdependencies', ingredient).subscribe(deps => {
      this.dependentMeals = deps;
      if (this.dependentMeals.length > 0) {
        this.dependentMealsInformation = 'Deletion not possible, ' + ingredient.name + ' is still in use for these Meals: ';
        this.dependentMeals.forEach(dep => this.dependentMealsInformation += ' ' + dep.name);
        this.dependentMeals = [];
      } else {
        this.http.post<Ingredient[]>('/api/ingredients/delete', ingredient).subscribe(ingredients => this.ingredients = ingredients);
        this.dependentMeals = [];
      }
    });
  }
}
