import {Meal} from './meal';
import {Process} from './process';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  processList: Process[] = [];

  getProcessList() {
    return this.processList;
  }

  addProcess(process: Process) {
    this.processList.push(process);
  }

}
