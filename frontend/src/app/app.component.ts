import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService } from './order.service';
import {Meal} from './meal';
import {MealDTO} from './mealDTO';
import {HttpClient} from '@angular/common/http';
import {User} from './User';
import {SecurityService} from './security.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  newMeals: MealDTO[];
  name = '';
  securityService: SecurityService;
  @Input()
  meals: Meal[];
  orderService: OrderService;
  sessionUser: User|null = null;
  constructor(private http: HttpClient, orderService: OrderService, securityService: SecurityService) {
    this.orderService = orderService;
    this.securityService = securityService;
  }

  ngOnInit(): void {
    // this.http.get<Meal[]>('/api/meals/order').subscribe(meals => this.meals = meals);
    // // Diese Methoden sollten bei Gelegenheit in den/ einen Service ausgelagert werden? AK
    // this.resetNewMeal();
    // this.newMeals = [];
    this.securityService.getSessionUser().subscribe(
      u => this.sessionUser = u
    );
  }

  resetNewMeal() {
    this.name = '';
  }

  logout() {
    this.securityService.logout();
    // this.loginData.username = null;
    // this.loginData.password = null;
    this.sessionUser = null;
  }
}
