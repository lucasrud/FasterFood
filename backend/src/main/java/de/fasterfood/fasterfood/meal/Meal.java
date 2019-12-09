package de.fasterfood.fasterfood.meal;

import de.fasterfood.fasterfood.ingredient.Ingredient;

import javax.naming.InsufficientResourcesException;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class Meal {
    @GeneratedValue
    private int id;

    private String name;

    private int purchasePrice;
    private int retailPrice;
    private int profit;

    @ManyToMany
    private List<Ingredient> ingredients;

    public Meal(String name, int purchasePrice, int retailPrice, int profit, List<Ingredient> ingredients) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.profit = profit;
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

    public int getPurchasePrice() {
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

    public int getProfit() {
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
