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


//
//    @OneToMany(mappedBy = "meal")
//    private List<Recipe> recipe;

    private String name;
    private double purchasePrice;
    private double retailPrice;
    private double profit;

//    @ManyToMany
//    private List<Ingredient> ingredients;


    public Meal(String name, double retailPrice, List<Ingredient> ingredients, HashMap amountOfIngredient) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.profit = retailPrice - purchasePrice;
//        this.ingredients = ingredients;
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

//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public void setRecipe(List<Recipe> recipe){
//        this.recipe = recipe;
//    }
//
//    public void addInstruction(Recipe recipe){
//        this.recipe.add(recipe);
//    }
//
//    public void deleteInstruction(Recipe recipe){
//        this.recipe.remove(recipe);
//    }
//
//    public List<Recipe> getRecipe() {
//
//        return recipe;
//    }

}
