import { Component, OnInit } from '@angular/core';
import {Ingredient} from '../ingredient';
import {Process} from '../process';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-finance',
  templateUrl: './finance.component.html',
  styleUrls: ['./finance.component.css']
})
export class FinanceComponent implements OnInit {

  processes: Process[];

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<Process[]>('/api/finance').subscribe( processes => this.processes = processes);
  }

}
