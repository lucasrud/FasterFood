import {Process} from './process';

export interface Order {

  processList: Process[];
  date: number; // todo    private Date date;
  retailPrice: number;
}
