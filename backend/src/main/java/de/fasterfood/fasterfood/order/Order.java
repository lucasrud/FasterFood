package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.process.Process;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Order {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Process> processList;

    private LocalDate date;
    private LocalTime time;

    private int retailPrice;


    public Order(LocalDate date, LocalTime time, List<Process> processes) {
        this.date = date;
        this.time = time;
        this.processList = processes;
        this.retailPrice=0;
        for(Process process: processes){
            retailPrice+=process.getRetailPrice();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}
