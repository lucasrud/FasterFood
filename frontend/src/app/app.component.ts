import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService } from './order.service';
import {TestService} from './testservice';
import {Meal} from './meal';
import {MealDTO} from './mealDTO';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  title = 'fasterfood-angular';

  newMeals: MealDTO[];
  name = '';
  @Input()
  meals: Meal[];
  orderService: OrderService;
  testService: TestService;

  constructor(private http: HttpClient, orderService: OrderService, testService: TestService) {
    this.orderService = orderService;
    this.testService = testService;
  }

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/fasterfood/order').subscribe(meals => this.meals = meals);
    // Diese Methoden sollten bei Gelegenheit in den/ einen Service ausgelagert werden? AK
    this.resetNewMeal();
    this.newMeals = [];
  }

  addMeal(nameE, priceE) {
    const m: MealDTO = {
      name: nameE,
      price: priceE,
    };
    console.log('aa');
    this.http.post<Meal[]>('/api/fasterfood/addMeal', m).subscribe(meals => this.meals = meals);
    this.newMeals.push(m);
    this.resetNewMeal();
  }

  deleteMeal(mealToBeDeleted) {
    this.http.post<Meal[]>('/api/fasterfood/deleteMeal', mealToBeDeleted).subscribe(meals => this.meals = meals);
  }

  resetNewMeal() {
    this.name = '';
  }

  addProcess(mealName: Meal) {
    this.orderService.addToMealList(mealName);
    this.orderService.getOrderCost();
  }
}
