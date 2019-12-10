import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import {TestService} from '../testservice';
import { OrderService } from '../order.service';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  title = 'order Components';
  meals: Meal[];
  ingredients: string[];
  orderService: OrderService;
  testService: TestService;
  orderCost: BehaviorSubject<number>;

  constructor(testservice: TestService, orderService: OrderService) {
    this.orderService = orderService;
    this.ingredients = testservice.getIngredients();
    this.meals = orderService.getMealList();
  }

  ngOnInit(): void {
    const orderCost = this.orderService.getOrderCost();
  }

}
