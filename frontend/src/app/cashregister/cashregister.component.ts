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

  title = 'fasterfood-angular';

  newMeals: MealDTO[];
  name = '';
  @Input()
  meals: Meal[];
  orderService: OrderService;

  constructor(private http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
  }

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/fasterfood/order').subscribe(meals => this.meals = meals);
    // Diese Methoden sollten bei Gelegenheit in den/ einen Service ausgelagert werden? AK
    this.resetNewMeal();
    this.newMeals = [];
  }

  addMeal(nameE, priceE) {
    const m: MealDTO = {
      name: nameE,
      price: priceE,
    };
    console.log('aa');
    this.http.post<Meal[]>('/api/fasterfood/addMeal', m).subscribe(meals => this.meals = meals);
    this.newMeals.push(m);
    this.resetNewMeal();
  }

  deleteMeal(mealToBeDeleted) {
    this.http.post<Meal[]>('/api/fasterfood/deleteMeal', mealToBeDeleted).subscribe(meals => this.meals = meals);
  }


  resetNewMeal() {
    this.name = '';
  }

  addProcess(mealName: Meal) {
    this.orderService.addToMealList(mealName);
    this.orderService.getOrderCost();
  }

}
