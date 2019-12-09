import {Meal} from './meal';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class TestService {

  meals: Meal[] = [];
  ingredients: string[] = ['Fladenbrot', 'Tomate', 'Zwiebel'];

  getIngredients(): string[] {
    return this.ingredients;
  }

  getMeals() {
      for (let i = 0; i < 5; i++) {

        const meal: Meal = {
          name: 'Döner',
          purchasePrice: 2,
          retailPrice: 5,
          profit: 3,
          ingredients: this.ingredients,
        };

        this.meals.push(meal);
      }
      return this.meals;
  }

}
