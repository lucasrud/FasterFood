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
    this.http.get<Meal[]>('/api/meals').subscribe( meals => this.meals = meals);
  }

  changePrice(meal, price) {
    if (!isNaN(Number(price)) && !(price === '')) {
      meal.retailPrice = price;
      this.http.post<Meal[]>('/api/meals/price', meal).subscribe(meals => this.meals = meals);
    }
  }
}
