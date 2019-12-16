package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.editMeal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RecipeService {

    RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllByMeal(Meal meal) {
        return recipeRepository.findAllByMealId(meal.getId());
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}
