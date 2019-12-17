import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Ingredient} from './ingredient';
import {Recipe} from './recipe';
import {variable} from '@angular/compiler/src/output/output_ast';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private response = 1;
  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;
  dbIngredients: Ingredient[];               // All current ingredients in stock
  currentMealRecipes: Recipe[] = [];          // Recipes needed for the whole current Meal
  currentOrderRecipes: Recipe[] = [];     // Ingredients needed for the whole current Orderlist

  constructor(private http: HttpClient) {
    const initialOrders: Meal[] = [];
    this.meals = new BehaviorSubject<Meal[]>(initialOrders);
    this.http.get<Ingredient[]>('/api/ingredients').subscribe(ingredients => this.dbIngredients = ingredients);
    this.dbIngredients = [];               // All current ingredients in stock
    this.currentMealRecipes = [];          // Recipes needed for the whole current Meal
    this.currentOrderRecipes = [];     // Ingredients needed for the whole current Orderlist
  }

  enoughIngredientsInStockCheck(meal): Observable<boolean> {
    const bool = new BehaviorSubject<boolean>(false);

    this.http.post<Recipe[]>('/api/recipes/meal', meal).subscribe(recipes => {
      this.currentMealRecipes = recipes;
      let containsRecipe = false;
      let nextBool = true;

      for (const recipe of this.currentMealRecipes) {

        for (const currentOrderRecipe of this.currentOrderRecipes) {
          containsRecipe = false;
          if (currentOrderRecipe.ingredient.name === recipe.ingredient.name) {
            currentOrderRecipe.amount += recipe.amount;
            containsRecipe = true;
          }
        }

        if (!containsRecipe) {
          this.currentOrderRecipes.push(recipe);
        }

        for (const currentOrderRecipe of this.currentOrderRecipes) {
          for (const dbingredient of this.dbIngredients) {
            if (currentOrderRecipe.ingredient.name === dbingredient.name) {
              if (currentOrderRecipe.amount > dbingredient.stock) {
                nextBool = false;
              }
            }
          }
        }
      }
      bool.next(nextBool);
    });
    return bool;
  }

  addToMealList(meal: Meal): void {
    this.currentCost += meal.retailPrice;
    this.meals.next([...this.meals.value, meal]);
  }

  getOrderCost(): number {
    return this.currentCost;
  }

  deleteMeal(index: number): void {
    const newMeals: Meal[] = [];
    let i = 0;
    for (const m of this.meals.value) {
      if (!(index === i)) {
        newMeals.push(m);
      } else {
        this.currentCost -= m.retailPrice;
      }
      i++;
    }
    this.meals.next([...newMeals]);
  }

  getMeals(): BehaviorSubject<Meal[]> {
    return this.meals;
  }

  order(meals) {
    alert('Order submitted');
    this.currentCost = 0;
    this.http.post<Meal[]>('/api/order', meals).subscribe(m => this.meals.next([]));
  }
}
