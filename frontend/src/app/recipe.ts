import {Ingredient} from './ingredient';
import {Meal} from './meal';

export interface Recipe {
  id: number;
  mealId: number;
  ingredient: Ingredient;
  amount: number;
}
