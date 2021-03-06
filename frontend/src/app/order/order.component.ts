import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import { OrderService } from '../order.service';
import {User} from '../User';


@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  meals: Meal[];
  orderService: OrderService;
  orderCostNum = 0;
  sessionUser: User|null = null;

  constructor(orderService: OrderService) {
    this.orderService = orderService;
  }

  ngOnInit(): void {
    const meals$ = this.orderService.getMeals();
    meals$.subscribe(meals => {
      this.meals = meals;
      this.orderCostNum = this.orderService.getOrderCost();
    });
  }

  deleteMealFromOrder(index: number) {
    this.orderService.deleteMealFromCart(index);
  }
  order(meals: Meal[]) {
    this.orderService.order(meals);
  }
}
