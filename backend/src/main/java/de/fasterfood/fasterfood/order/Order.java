package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.process.Process;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String time;
    private double retailPrice;


    public Order(LocalDate date, LocalTime time, List<Process> processes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.date = date.toString();
        this.time = formatter.format(time);
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

    public String getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date.toString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time.toString();
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}
