import {Process} from './process';
import {Time} from '@angular/common';

export interface Order {
  id: number;
  processList: Process[];
  date: string; // todo    private Date date;
  time: string;
  retailPrice: number;
}
