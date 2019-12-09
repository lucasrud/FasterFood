import {Meal} from './meal';
import {Order} from './order';

export interface Process {

  order: Order;
  meal: Meal;
  retailPrice: number;
  quantity: number;
}
