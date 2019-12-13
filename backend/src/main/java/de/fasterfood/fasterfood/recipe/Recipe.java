package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.editMeal.Meal;

import javax.persistence.*;

@Entity
public class Recipe {

    @EmbeddedId
    IngredientMealKey id;

    @ManyToOne
    @MapsId("meal_id")
    @JoinColumn(name = "meal_id")
    Meal meal;

    @ManyToOne
    @MapsId("ingredients_id")
    @JoinColumn(name = "ingredients_id")
    Ingredient ingredient;

    int amount;

    public Recipe() {
    }

    public Recipe(Meal meal, Ingredient ingredient, int amount){
        this.meal = meal;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public IngredientMealKey getId() {
        return id;
    }

    public void setId(IngredientMealKey id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
