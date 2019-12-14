import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OrderService {
  constructor(private http: HttpClient) {
    const initialOrders: Meal[] = [];
    this.meals = new BehaviorSubject<Meal[]>(initialOrders);
  }

  private response = 1;
  private test: boolean;
  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;

  addToMealList(meal: Meal): void {
    this.currentCost += meal.retailPrice;
    this.meals.next([...this.meals.value, meal]);
  }

  deleteMeal(index: number): void {
    const newMeals: Meal[] = [];
    let i = 0;
    for (const m of this.meals.value) {
      if (!( index === i )) {
        newMeals.push(m);
      } else {
        this.currentCost -= m.retailPrice;
      }
      i++;
    }
    this.meals.next([...newMeals]);
  }
  getMeals(): BehaviorSubject<Meal[]> {
    return this.meals;
  }

  getOrderCost(): number {
    return this.currentCost;
  }
  order(meals) {
    this.http.post<number>('/api/fasterfood/orderCheck', meals).subscribe(response => this.response = response);
    if (this.response === 1) {
      alert('Order submitted');
      this.currentCost = 0;
      this.http.post<Meal[]>('/api/fasterfood/order', meals).subscribe(m => this.meals.next([]));
    } else {
      prompt('Not enough stock to proceed with order');
    }
  }
}
