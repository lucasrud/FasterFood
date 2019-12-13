import {Recipe} from './recipe';

export interface Meal {
  id: number;
  name: string;
  purchasePrice: number;
  retailPrice: number;
  profit: number;
  ingredients: string[];
}
