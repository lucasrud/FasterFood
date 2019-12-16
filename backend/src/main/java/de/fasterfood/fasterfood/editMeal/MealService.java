package de.fasterfood.fasterfood.editMeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public MealService(MealRepository mealRepository, RecipeRepository recipeRepository){
        this.mealRepository = mealRepository;
        this.recipeRepository = recipeRepository;
    }

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public void changeRetailPrice(Meal meal){
        mealRepository.save(meal);
    }

    public void addMeal(MealDTO meal) {

        Meal newMeal = new Meal(meal.getName(), meal.getPrice());
        mealRepository.save(newMeal);

        for (RecipeDTO recipeDTO : meal.getRecipeDTOS()) {
            Recipe recipe = new Recipe(newMeal.getId(), recipeDTO.getIngredient(), recipeDTO.getAmount());
            recipeRepository.save(recipe);
        }
        newMeal.setPurchasePrice(getPurchasePrice(newMeal));
        mealRepository.save(newMeal);
    }

    public void deleteMeal(Meal meal) {
        mealRepository.delete(meal);
    }

    public double getPurchasePrice(Meal meal){
        List<Recipe> recipe = recipeRepository.findAllByMealId(meal.getId());
        double purchasePrice = 0;
        for (Recipe instruction : recipe) {
            purchasePrice += instruction.getIngredient().getPurchasePrice()*instruction.getAmount();
        }
        return purchasePrice;
    }
}
