package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

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

    public void changeStock(Ingredient ingredient){
        System.out.println(ingredient.getStock() + " " + ingredient.getName());
        Optional<Ingredient> foundOptionalIngredient = ingredientRepository.findById(ingredient.getId());
        Ingredient changedIngredient = foundOptionalIngredient.get();
        changedIngredient = ingredient;
        ingredientRepository.save(changedIngredient);
    }

}
