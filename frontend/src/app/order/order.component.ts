import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import { OrderService } from '../order.service';
import {Ingredient} from '../ingredient';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  meals: Meal[];
  orderService: OrderService;
  orderCostNum = 0;

  constructor(private  http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
  }

  ngOnInit(): void {
    const meals$ = this.orderService.getMeals();
    meals$.subscribe(meals => {
      this.meals = meals;
      this.orderCostNum = this.orderService.getOrderCost();
    });
  }

 checkOutOrder(meals) {

  this.http.post<Meal[]>('/api/fasterfood/order', meals).subscribe( mealz => this.meals = mealz);
  }

}
