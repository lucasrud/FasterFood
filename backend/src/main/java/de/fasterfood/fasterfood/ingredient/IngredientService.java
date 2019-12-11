package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public void addStock(Ingredient ingredient, int value){
        ingredient.addStock(value);
    }

    public void useForMeal(Meal meal){

        for (Ingredient ingredient : meal.getIngredients()){

        }
    }
    public void changePurchasePrice(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }
    public void changeStock(Ingredient ingredient, int value){
        ingredient.setStock(value);
        ingredientRepository.save(ingredient);
    }

}
