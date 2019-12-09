package de.fasterfood.fasterfood.meal;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import javax.persistence.*;
import java.util.List;


@Entity
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double purchasePrice;
    private int retailPrice;
    private double profit;

    @ManyToMany
    private List<Ingredient> ingredients;

    public Meal(String name, int retailPrice, List<Ingredient> ingredients) {
        this.name = name;
        for (Ingredient ingredient : ingredients){
            this.purchasePrice += ingredient.getPurchasePrice();
        }
        this.retailPrice = retailPrice;
        this.profit = retailPrice - purchasePrice;
        this.ingredients = ingredients;
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

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
