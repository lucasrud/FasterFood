package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.editMeal.Meal;
import de.fasterfood.fasterfood.editMeal.MealRepository;
import de.fasterfood.fasterfood.recipe.Recipe;
import de.fasterfood.fasterfood.recipe.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private MealRepository mealRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, MealRepository mealRepository){
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
    }

    public void decreaseStockFromOrder(Ingredient ingredient, int amount){
        ingredient.addStock(amount);
        ingredientRepository.save(ingredient);
    }

    public void changePurchasePrice(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
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

    public List<Meal> checkDependencies(Ingredient ingredient) {

        List<Meal> dependentMeals = new ArrayList<>();
        List<Recipe> dependentRecipes = recipeRepository.findAllByIngredientId(ingredient.getId());

        for (Recipe dependentRecipe : dependentRecipes) {
            dependentMeals.add(mealRepository.findById(dependentRecipe.getMealId()).get());
        }
        return dependentMeals;
    }
}
