import {Meal} from './meal';

export interface Ingredient {
  id: number;
  name: string;
  purchasePrice: number;
  stock: number;
  mealList: Meal[];
}
