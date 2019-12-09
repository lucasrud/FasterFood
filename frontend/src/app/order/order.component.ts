import { Component, OnInit } from '@angular/core';
import {Meal} from '../meal';
import {TestService} from '../testservice';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  // Bisher ales nur Platzhalter

  title = 'order Component';
  mealz: Meal[];
  ingredients: string[];

  constructor(testservice: TestService) {

    this.ingredients = testservice.getIngredients();
    this.mealz = testservice.getMeals();
  }

  ngOnInit(): void {
  }

}
