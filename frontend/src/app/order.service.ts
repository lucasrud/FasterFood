import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Process} from './process';
import {BehaviorSubject, Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
    const initialOrders: Meal[] = [];
    this.meals = new BehaviorSubject<Meal[]>(initialOrders);
  }

  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;

  addToMealList(meal: Meal): void {
    this.currentCost += meal.retailPrice;
    this.meals.next([...this.meals.value, meal]);
  }

  deleteMeal(meal: Meal): void {
    const index = this.meals.value.indexOf(meal);
    delete this.meals.value[index];
    this.meals.next(this.meals.value);
  }
  getMeals(): BehaviorSubject<Meal[]> {
    return this.meals;
  }

  getOrderCost(): number {
    return this.currentCost;
  }

  addMeals(meals) {
    this.http.post('/api/fasterfood/addMeals', meals);
  }

}
