package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.process.Process;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order")
    private List<Process> processList;

    private LocalDate date;
    private LocalTime time;
    private double retailPrice;


    public Order(LocalDate date, LocalTime time, List<Process> processes) {
        this.date = date;
        this.time = time;
        this.processList = processes;
        this.retailPrice = 0;
        for(Process process: processes){
            retailPrice += process.getRetailPrice();
        }
    }

    public Order() {
    }

    public Integer getId() {
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

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}
