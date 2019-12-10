package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.meal.Meal;

import javax.persistence.*;

@Entity
public class Recipe {

    @EmbeddedId
    IngredientMealKey id;

    @ManyToOne
    @MapsId("meal_id")
    @JoinColumn(name = "meal_id")
    Meal meal;

}
