import {Component, Input, OnInit} from '@angular/core';
import {MealDTO} from '../mealDTO';
import {Meal} from '../meal';
import {OrderService} from '../order.service';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-cashregister',
  templateUrl: './cashregister.component.html',
  styleUrls: ['./cashregister.component.css']
})
export class CashregisterComponent implements OnInit {

  newMeals: MealDTO[];
  name = '';
  @Input()
  meals: Meal[];
  orderService: OrderService;

  constructor(private http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
  }

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/order').subscribe(meals => this.meals = meals);
    this.resetNewMeal();
    this.newMeals = [];
  }

  resetNewMeal() {
    this.name = '';
  }

  addProcess(meal: Meal) {

    this.orderService.enoughIngredientsInStockCheck(meal).subscribe((bool) => {
      if (bool) {
        this.orderService.addToMealList(meal);
        this.orderService.getOrderCost();
      }
    });
  }
}
