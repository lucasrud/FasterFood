import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService } from './order.service';
import {Meal} from './meal';
import {MealDTO} from './mealDTO';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  newMeals: MealDTO[];
  name = '';
  @Input()
  meals: Meal[];
  orderService: OrderService;

  constructor(private http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
  }

  // Können die auskommentierten Inhalte von Ingredient.service gelöscht werden? AK

  ngOnInit(): void {
    this.http.get<Meal[]>('/api/meals/order').subscribe(meals => this.meals = meals);
    // Diese Methoden sollten bei Gelegenheit in den/ einen Service ausgelagert werden? AK
    this.resetNewMeal();
    this.newMeals = [];
  }

  resetNewMeal() {
    this.name = '';
  }
}
