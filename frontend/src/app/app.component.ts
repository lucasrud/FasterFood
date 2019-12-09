import {Component, OnInit} from '@angular/core';
import { OrderService } from './orderservice';
import {Meal} from './meal';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
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
    this.http.get<Meal[]>('/fasterfood/order').subscribe( meals => this.meals = meals);
  }

  addProcess() {
    // Todo
    // this.orderService.addProcess();
  }

}
