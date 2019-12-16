import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {Ingredient} from './ingredient';
import {Recipe} from './recipe';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private response = 1;
  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;
  dbIngredients = [];               // All current ingredients in stock
  // dbRecipes = [];
  currentMealRecipes = [];          // Recipes needed for the whole current Meal
  currentOrderRecipes = [];     // Ingredients needed for the whole current Orderlist

  constructor(private http: HttpClient) {
    const initialOrders: Meal[] = [];
    this.meals = new BehaviorSubject<Meal[]>(initialOrders);
    this.http.get<Ingredient[]>('/api/ingredients').subscribe(ingredients => this.dbIngredients = ingredients);
    // this.dbIngredients = [];               // All current ingredients in stock
    // this.dbRecipes = [];
    // this.currentMealRecipes = [];          // Recipes needed for the whole current Meal
    // this.currentOrderRecipes = [];     // Ingredients needed for the whole current Orderlist
  }

  enoughIngredientsInStockCheck(meal): boolean {

    let returnBool = false;

    this.http.post<Recipe[]>('/api/recipes/meal', meal).subscribe(recipes => {
      this.currentMealRecipes = recipes;

      // alert('currentOrderRecipes ' + this.currentOrderRecipes);
      // alert('dbngredients ' + this.dbIngredients);
      // alert('currentMealRecipes ' + this.currentMealRecipes);

      let containsRecipe = false;

      for (const recipe of this.currentMealRecipes) {
        for (const currentOrderRecipe of this.currentOrderRecipes) {
          if (currentOrderRecipe.ingredient.name === recipe.ingredient.name) {
            currentOrderRecipe.amount += recipe.amount;
            containsRecipe = true;
            break;
          }
        }
        if (!containsRecipe) {
          this.currentOrderRecipes.push(recipe);
        }

        for (const currentOrderRecipe of this.currentOrderRecipes) {
          for (const dbingredient of this.dbIngredients) {
            if (currentOrderRecipe.ingredient.name === dbingredient.name) {
              // tslint:disable-next-line:max-line-length
              // alert('currentorderRecipe.ingredient.name: ' + currentOrderRecipe.ingredient.name + ' urrentOrderRecipe.amount ' + currentOrderRecipe.amount + ' dbingredient: ' + dbingredient.name + ' ' + dbingredient.stock);
              if (currentOrderRecipe.amount > dbingredient.stock) {
                return false;
              } else {
                break;
              }
            }
          }
        }
      }
      returnBool = true;

    });
    return returnBool;
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
