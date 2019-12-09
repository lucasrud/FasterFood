package de.fasterfood.fasterfood.process;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.order.Order;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Process {

    @GeneratedValue
    private int id;

    @ManyToOne
    private Order order;
    @OneToOne
    private Meal meal;

    private int retailPrice;
    private int quantity;

    public Process (Meal meal, int retailPrice) {
//        this.order = order;
        this.meal = meal;
        this.retailPrice= retailPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}

