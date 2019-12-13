import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {Recipe} from './recipe';


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

      const bla: string[] = ['Cola', 'Cola', 'Döner', 'Pizza', 'Pasta', 'Haxe'];
      const blacost: number[] = [1.50, 1.50, 4.50, 7.20, 5.90, 9.99];

      for (let i = 1; i < 6; i++) {

        const meal: Meal = {
          id: i,
          name: bla[i],
          purchasePrice: 2,
          retailPrice: blacost[i],
          profit: 3,
          ingredients: this.ingredients,
        };

        this.meals.push(meal);
      }
      return this.meals;
  }

}
