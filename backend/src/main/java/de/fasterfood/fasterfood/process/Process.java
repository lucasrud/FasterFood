package de.fasterfood.fasterfood.process;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.order.Order;
import javax.persistence.*;


@Entity
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Order order;
    @OneToOne
    private Meal meal;

    private double retailPrice;
    private int quantity;


    public Process (Meal meal, double retailPrice) {
        this.meal = meal;
        this.retailPrice= retailPrice;
        this.quantity=1;
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

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public int getQuantity() {
        return quantity;
    }
}
