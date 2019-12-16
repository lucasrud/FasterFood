package de.fasterfood.fasterfood.editMeal;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;


@Entity
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
