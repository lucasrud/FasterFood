import {Meal} from './meal';
import {Order} from './order';

export interface Process {

  id: number;
  order: Order;
  meal: Meal;
  retailPrice: number;
  quantity: number;
}
