package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.meal.Meal;
import de.fasterfood.fasterfood.recipe.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findAll();
    List<Recipe> findAllByMealId(Integer mealId);

}
