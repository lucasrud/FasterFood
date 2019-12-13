import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService} from '../order.service';
import {TestService} from '../testservice';
import {Meal} from '../meal';
import {MealDTO} from '../mealDTO';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-meals',
  templateUrl: './meals.component.html',
  styleUrls: ['./meals.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MealsComponent implements OnInit {

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
    this.http.get<Meal[]>('/api/meals').subscribe(meals => this.meals = meals);
    // Diese Methoden sollten bei Gelegenheit in den/ einen Service ausgelagert werden? AK
    this.resetNewMeal();
    this.newMeals = [];
  }

  addMeal(nameE, priceE) {
    if (!isNaN(Number(priceE)) && !(priceE === '')) {
      const m: MealDTO = {
        name: nameE,
        price: priceE,
      };

      this.http.post<Meal[]>('/api/fasterfood/addMeal', m).subscribe(meals => this.meals = meals);
      this.newMeals.push(m);
      this.resetNewMeal();
    }
  }

  deleteMeal(mealToBeDeleted) {
    this.http.post<Meal[]>('/api/fasterfood/deleteMeal', mealToBeDeleted).subscribe(meals => this.meals = meals);
  }

  resetNewMeal() {
    this.name = '';
  }

  changePrice(meal, price) {
    if (!isNaN(Number(price)) && !(price === '')) {
      meal.retailPrice = price;
      this.http.post<Meal[]>('/api/meals/price', meal).subscribe(meals => this.meals = meals);
    }
  }
}
