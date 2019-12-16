package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.ingredient.Ingredient;

public class RecipeDTO {
    private Ingredient ingredient;
    int amount;

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
