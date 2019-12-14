package de.fasterfood.fasterfood.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.fasterfood.fasterfood.ingredient.Ingredient;
import de.fasterfood.fasterfood.editMeal.Meal;

import javax.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mealId;

    @JsonIgnore
    @ManyToOne
    private Ingredient ingredient;

    int amount;

    public Recipe() {
    }

    public Recipe(Integer meal, Ingredient ingredient, int amount){
        this.mealId = meal;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeal() {
        return mealId;
    }

    public void setMealId(Integer mealId){
        this.mealId = mealId;
    }

    public Integer getMealId(){
        return this.mealId;
    }

    public void setMeal(Integer meal) {
        this.mealId = meal;
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
