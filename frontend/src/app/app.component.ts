import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { OrderService } from './order.service';
import {Meal} from './meal';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  // Bisher ales nur Platzhalter-

  title = 'fasterfood-angular';

  meals: Meal[];
  orderService: OrderService;

  constructor(private http: HttpClient, orderService: OrderService) {
    this.orderService = orderService;
  }

  ngOnInit(): void {
    this.meals = this.orderService.getAllMeals();
  }

  addProcess() {
    // Todo
    // this.orderService.addProcess();
  }

}
