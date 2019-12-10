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
  }

  private meals: Meal[] = [];
  processList: Process[] = [];
  mealList: Meal[] = [];
  currentCost: number;

  addToMealList(meal: Meal): void {
    this.mealList.push(meal);
  }

  getMealList(): Meal[] {
    return this.mealList;
  }

  setOrderCost(): number {

    for (const meal of this.mealList) {
      this.currentCost += meal.retailPrice;
    }

    console.log(this.currentCost);
    return this.currentCost;
  }

  getOrderCost(): number {
    return this.currentCost;
  }


}
