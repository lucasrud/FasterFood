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
  stockBool: number;
  httpMeal: Meal;

  constructor(private http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
    this.stockBool = 0;
    this.httpMeal = {
      id: 0,
    name: 'No',
    purchasePrice: 0,
    retailPrice: 0,
    profit: 0,
    };
    this.stockBool = 3;
  }

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/order').subscribe(meals => this.meals = meals);
    this.resetNewMeal();
    this.newMeals = [];
    // this.http.post<number>('/api/stockcheck', this.httpMeal).subscribe(returnVal => this.stockBool = returnVal);
  }

  resetNewMeal() {
    this.name = '';
  }

  addProcess(meal: Meal) {
      this.http.post<number>('/api/stockcheck', meal).subscribe(returnVal => {
        this.stockBool = returnVal;
        if (this.stockBool === 1) {
          this.orderService.addToMealList(meal);
          this.orderService.getOrderCost();
        }
      });
  }
}
