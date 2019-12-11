import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService } from './order.service';
import {TestService} from './testservice';
import {Meal} from './meal';
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

  meals: Meal[];
  orderService: OrderService;
  testService: TestService;

  constructor(private http: HttpClient, orderService: OrderService, testService: TestService) {
    this.orderService = orderService;
    this.testService = testService;
  }

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/fasterfood/order').subscribe( meals => this.meals = meals);
    // this.meals = this.orderService.fetchMeals();
  }

  addProcess(mealName: Meal) { // TODO erstmal Platzhalter nur mit Strings für die angeklickten Waren, später dann mit Process?
    this.orderService.addToMealList(mealName);
    this.orderService.getOrderCost();
  }

}
