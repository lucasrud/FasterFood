package de.fasterfood.fasterfood.editMeal;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.recipe.Recipe;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;


@Entity
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    Recipe recipe;

    private String name;
    private double purchasePrice;
    private double retailPrice;
    private double profit;

    @ManyToMany
    private List<Ingredient> ingredients;

    private HashMap<String, Integer> amountOfIngredient;

    public Meal(String name, double retailPrice, List<Ingredient> ingredients, HashMap amountOfIngredient) {
        this.name = name;
        for (Ingredient ingredient : ingredients){
            this.purchasePrice += ingredient.getPurchasePrice();
        }
        this.retailPrice = retailPrice;
        this.profit = retailPrice - purchasePrice;
        this.ingredients = ingredients;
        this.amountOfIngredient = amountOfIngredient;
    }

    public Meal() {
    }

    public Integer getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recipe getRecipeSet() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
