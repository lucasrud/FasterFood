import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import {TestService} from '../testservice';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  // Bisher ales nur Platzhalter

  title = 'order Components';
  meals: Meal[];
  mealz: Meal[];
  ingredients: string[];
  orderService: OrderService;
  testService: TestService;

  constructor(testservice: TestService, orderService: OrderService) {

    this.ingredients = testservice.getIngredients();
    this.mealz = orderService.getTempProcessList();
  }

  ngOnInit(): void {
  }

}
