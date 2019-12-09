import {Meal} from './meal';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  processList: Meal[] = [];

  getProcessList(): string[] {
    return this.processList;
  }

  addProcess(process: Process) {
    this.processList.push(process);
  }

}
