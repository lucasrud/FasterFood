import {Meal} from './meal';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Process} from './process';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  meals: Meal[] = [];
  processList: Process[] = [];
  tempProcessList: Meal[] = [];

  // getProcessList() {
  //   return this.processList;
  // }
  //
  // addProcess(process: Process) {
  //   this.processList.push(process);
  // }

  addTempProcessList(tempProcessString: Meal) {
    this.tempProcessList.push(tempProcessString);
  }

  getTempProcessList() {
    return this.tempProcessList;
  }

  // getAllMeals() {  // steht jetzt direkt in app.component.ts
  //     this.http.get<Meal[]>('/api/fasterfood/order').subscribe( meals => this.meals = meals);
  // }
}
