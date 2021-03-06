package de.fasterfood.fasterfood.recipe;

import de.fasterfood.fasterfood.editMeal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

//    @GetMapping("/api/recipes")  // wird das benötigt?
//    public List<Recipe> getRecipes() {
//        return recipeService.findAll();
//    }

    @PostMapping("/api/recipes/meal")
    public List<Recipe> getRecipeByMeal(@RequestBody Meal meal) {
        return recipeService.findAllByMeal(meal);
    }

}
