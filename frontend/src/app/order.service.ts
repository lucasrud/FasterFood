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

  constructor(private http: HttpClient) {
    const initialOrders: Meal[] = [];
    this.meals = new BehaviorSubject<Meal[]>(initialOrders);
    this.dbIngredients = [];               // All current ingredients in stock
    this.currentMealRecipes = [];              // Recipes needed for the whole current Meal
    this.currentOrderIngredients = [];     // Ingredients needed for the whole current Orderlist
  }

  private response = 1;
  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;
  dbIngredients: Ingredient[];               // All current ingredients in stock
  currentMealRecipes: Recipe[];              // Recipes needed for the whole current Meal
  currentOrderIngredients: Ingredient[];     // Ingredients needed for the whole current Orderlist



  enoughIngredientsInStockCheck(meal): boolean {

    this.http.get<Ingredient[]>('/api/ingredients').subscribe( ingredients => this.dbIngredients = ingredients);
    this.http.post<Recipe[]>('/api/recipes/meal', meal).subscribe( recipes => this.currentMealRecipes = recipes);

    alert('currentOrderIngredients ' + this.currentOrderIngredients);
    alert('dbngredients ' + this.dbIngredients);
    alert('currentMealRecipes ' + this.currentMealRecipes);

    for (const recipe of this.currentMealRecipes) {
      if (!this.currentOrderIngredients.includes(recipe.ingredient)) {
        this.currentOrderIngredients.push(recipe.ingredient);
      } else {
        for (const currentOrderIngredient of this.currentOrderIngredients) {
          if (currentOrderIngredient === recipe.ingredient) {
            currentOrderIngredient.stock += recipe.ingredient.stock;
          }
        }
      }

      for (const currentOrderIngredient of this.currentOrderIngredients) {
        for (const dbingredient of this.dbIngredients) {
          if (currentOrderIngredient === dbingredient) {
            // tslint:disable-next-line:max-line-length
            alert('currentorderingredient: ' + currentOrderIngredient.name + ' ' + currentOrderIngredient.stock + ' dbingredient: ' + dbingredient.name + ' ' + dbingredient.stock);
            if (currentOrderIngredient.stock > dbingredient.stock) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

    addToMealList(meal: Meal): void {

    // Todo soll nur geaddet werden, wenn genug stock auf Lager ist
     if (this.enoughIngredientsInStockCheck) {
      this.currentCost += meal.retailPrice;
      this.meals.next([...this.meals.value, meal]);
     }
  }

  getOrderCost(): number {
    return this.currentCost;
  }

  deleteMeal(index: number): void {
    const newMeals: Meal[] = [];
    let i = 0;
    for (const m of this.meals.value) {
      if (!( index === i )) {
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
