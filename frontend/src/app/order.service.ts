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

  getMeals(): BehaviorSubject<Meal[]> {
    return this.meals;
  }

  getOrderCost(): number {
    return this.currentCost;
  }

  checkout(){
    this.http.post<Meal[]>('/api/fasterfood/order', {}).subscribe( meals => this.meals = meals);
  }

}
