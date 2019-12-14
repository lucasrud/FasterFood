import {Ingredient} from './ingredient';

export interface Recipe {
  id: number;
  mealId: number;
  ingredient: Ingredient;
  amount: number;
}
