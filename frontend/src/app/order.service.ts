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

  private meals: BehaviorSubject<Meal[]>;
  currentCost = 0;

  addToMealList(meal: Meal): void {
    this.currentCost += meal.retailPrice;
    this.meals.next([...this.meals.value, meal]);
  }

  deleteMeal(meal: Meal): void {
    const newMeals: Meal[] = [];
    for (const m of this.meals.value) {
      if (!(m === meal) && !(this.meals.value.indexOf(m) === this.meals.value.indexOf(meal))) {
        newMeals.push(m);
      }
    }
    this.meals.next([...newMeals]);
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
