package de.fasterfood.fasterfood.editMeal;

import javax.persistence.*;


@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getName() {
        return name;
    }

    private String name;
    private double purchasePrice;
    private double retailPrice;
    private double profit;

    public Meal(String name, double retailPrice) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.profit = retailPrice - purchasePrice;
    }

    public Meal() {
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    //TODO: purchaseprice muss ingrendwo gesettet werden
    public void setPurchasePrice(double purchasePrice) {

        this.purchasePrice = purchasePrice;
        this.profit = this.retailPrice - this.purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
