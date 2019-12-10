import {Meal} from './meal';

export interface Ingredient {

  name: string;
  purchasePrice: number;
  stock: number;
  mealList: Meal[];
}
