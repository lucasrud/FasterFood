import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-prices',
  templateUrl: './prices.component.html',
  styleUrls: ['./prices.component.css']
})
export class PricesComponent implements OnInit {

  meals: Meal[];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<Meal[]>('/api/price/meals').subscribe( meals => this.meals = meals);
  }
  changePrice(meal, price) {
    meal.retailPrice = price;
    this.http.post<Meal[]>('/api/price/meals', meal).subscribe( meals => this.meals = meals);
  }

}
