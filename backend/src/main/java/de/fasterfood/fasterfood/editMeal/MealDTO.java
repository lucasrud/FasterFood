package de.fasterfood.fasterfood.editMeal;

import de.fasterfood.fasterfood.recipe.RecipeDTO;

public class MealDTO {
    private String name;
    private double price;
    private RecipeDTO[] recipeDTOS;

    public MealDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RecipeDTO[] getRecipeDTOS() {
        return recipeDTOS;
    }

    public void setRecipeDTOS(RecipeDTO[] recipeDTOS) {
        this.recipeDTOS = recipeDTOS;
    }
}
