import {RecipeDTO} from './recipeDTO';

export interface MealDTO {
  name: string;
  price: number;
  recipeDTOS: RecipeDTO[];
}
