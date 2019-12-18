package de.fasterfood.fasterfood.process;

import de.fasterfood.fasterfood.editMeal.Meal;
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

    public Process() { }

    public Process (Meal meal, double retailPrice) {
        this.meal = meal;
        this.retailPrice = retailPrice;
        this.quantity = 1;
    }

    @Override
    public boolean equals(Object obj) {   // necessary for method OrderService.addOrderandProcess()

        if (obj == this) {
            return true;
        }
        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        Process objProcess = (Process)obj;

        return this.getMeal().getId() == objProcess.getMeal().getId();
    }

    public Integer getId() {
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
        this.retailPrice += this.meal.getRetailPrice();
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

