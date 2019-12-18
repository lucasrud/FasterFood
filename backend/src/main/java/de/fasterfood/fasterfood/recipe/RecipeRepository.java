package de.fasterfood.fasterfood.recipe;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findAll();
    List<Recipe> findAllByMealId(Integer mealId);
    List<Recipe> findAllByIngredientId(Integer ingredientId);

    Recipe findByIngredientIdAndMealId(Integer ingredientId, Integer id);
}
