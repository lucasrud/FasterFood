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

    public void decreaseStockFromOrder(Ingredient ingredient, int amount){
        ingredient.addStock(amount);
        ingredientRepository.save(ingredient);
    }

    public void changePurchasePrice(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void changeStock(Ingredient ingredient){

        Optional<Ingredient> foundOptionalIngredient = ingredientRepository.findById(ingredient.getId());
        Ingredient changedIngredient = foundOptionalIngredient.get();  // nochmal umschreiben?
        changedIngredient = ingredient;
        ingredientRepository.save(changedIngredient);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
