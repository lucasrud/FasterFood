import { Component, OnInit } from '@angular/core';
import {Process} from '../process';
import {HttpClient} from '@angular/common/http';
import {User} from '../User';


@Component({
  selector: 'app-finance',
  templateUrl: './finance.component.html',
  styleUrls: ['./finance.component.css']
})
export class FinanceComponent implements OnInit {

  processes: Process[];
  daily: boolean;
  sessionUser: User|null = null;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<Process[]>('/api/finance').subscribe( processes => this.processes = processes);
  }
  changeView() {
    if (this.daily) {
      this.daily = false;
      this.http.get<Process[]>('/api/finance').subscribe( processes => this.processes = processes);
    } else {
      this.daily = true;
      this.http.get<Process[]>('/api/finance/today').subscribe( processes => this.processes = processes);
    }
  }
  processesTotalTurnover(processes) {
    let sum = 0;
    for (const process of processes) {
      sum += process.retailPrice;
    }
    return sum;
  }
  processesTotalPurchasePrice(processes) {
    let sum = 0;
    for (const process of processes) {
      sum += process.meal.purchasePrice * process.quantity;
    }
    return sum;
  }
  processesTotalProfit(processes) {
    let sum = 0;
    for (const process of processes) {
      sum += process.meal.profit * process.quantity;
    }
    return sum;
  }

}
