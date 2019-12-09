package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.meal.Meal;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double purchasePrice;

    private int stock;

    @ManyToMany
    private List<Meal> mealList;

    public Ingredient(String name, double purchasePrice, int stock) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.stock = stock;
    }

    public void addMeal(Meal meal){
        if(!this.mealList.contains(meal)){
            this.mealList.add(meal);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
