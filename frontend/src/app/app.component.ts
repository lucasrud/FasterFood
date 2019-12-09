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
    this.meals = this.testService.getMeals();
    // this.meals = this.orderService.getAllMeals();
  }

  ngOnInit(): void {
  }

  addProcess(mealName: string) { // TODO erstmal Platzhalter nur mit Strings für die angeklickten Waren, später dann mit Process?
    this.orderService.addTempProcessList(mealName);
  }

}
