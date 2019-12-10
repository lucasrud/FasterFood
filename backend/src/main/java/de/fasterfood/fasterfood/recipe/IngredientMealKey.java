package de.fasterfood.fasterfood.recipe;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngredientMealKey implements Serializable {

    @Column(name = "meal_id")
    Integer mealId;

    @Column(name = "ingredients_id")
    Integer ingredientsId;


    public IngredientMealKey(){

    }


    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Integer getIngredientsId() {
        return ingredientsId;
    }

    public void setIngredientsId(Integer ingredientsId) {
        this.ingredientsId = ingredientsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientMealKey that = (IngredientMealKey) o;
        return mealId.equals(that.mealId) &&
                ingredientsId.equals(that.ingredientsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, ingredientsId);
    }
}
