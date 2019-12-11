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

  // Bisher ales nur Platzhalter-

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
    this.http.get<Meal[]>('/api/fasterfood/order').subscribe( meals => this.meals = meals);
    this.resetNewMeal();
    this.newMeals = [];
  }
  addMeal(nameE) {
    const m: MealDTO = {
      name: nameE,
      price: 6,
    };
    console.log('aa');

    this.http.post<Meal[]>('/api/fasterfood/addMeal', m).subscribe( meals => this.meals = meals);
    this.newMeals.push(m);
    this.resetNewMeal();
  }
  postMeals() {
    this.orderService.addMeals(this.newMeals);
}

  resetNewMeal() {
    this.name = '';
  }
  addProcess(mealName: Meal) { // TODO erstmal Platzhalter nur mit Strings für die angeklickten Waren, später dann mit Process?
    this.orderService.addToMealList(mealName);
    this.orderService.getOrderCost();
  }

}
