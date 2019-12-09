import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Process} from './process';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
    this.getAllMeals();
  }

  meals: Meal[] = [];
  processList: Meal[] = [];

  // getProcessList(): string[] {
  //   return this.processList;
  // }
  //
  // addProcess(process: Process) {
  //   this.processList.push(process);
  // }

  getAllMeals() {
      this.http.get<Meal[]>('/fasterfood/order').subscribe( meals => this.meals = meals);
      return this.meals;
  }
}
