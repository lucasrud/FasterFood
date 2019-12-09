package de.fasterfood.fasterfood.order;

import de.fasterfood.fasterfood.process.Process;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Order {
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Process> processList;

    private Date date;
    private Time time;

    private int net;
    private int gross;


    public Order(Date date, Time time, List<Process> processes) {
        this.date = date;
        this.time = time;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }
}
