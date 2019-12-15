package de.fasterfood.fasterfood.ingredient;

import de.fasterfood.fasterfood.editMeal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class IngredientController {

    IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }


    @GetMapping("/api/ingredients")
    public List<Ingredient> listAllIngredients(){
        return ingredientService.findAll();
    }

    @PostMapping("/api/ingredients/price")
    public List<Ingredient> changeIngredientPrices(@RequestBody Ingredient ingredient){
        ingredientService.changePurchasePrice(ingredient);
        return listAllIngredients();
    }

    @PostMapping("/api/ingredients/changestock")
    public List<Ingredient> changeStock(@RequestBody Ingredient ingredient){
        ingredientService.changeStock(ingredient);
        return listAllIngredients();
    }

    @PostMapping("/api/ingredients/add")
    public List<Ingredient> add(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
        return listAllIngredients();
    }

    @PostMapping("/api/ingredients/delete")
    public List<Ingredient> delete(@RequestBody Ingredient ingredient){
        ingredientService.deleteIngredient(ingredient);
        return listAllIngredients();
    }

    @PostMapping("/api/ingredients/mealdependencies") // Gehört das vielleicht eher in MealController, wenn es Meal returned?
    public List<Meal> mealdependencies(@RequestBody Ingredient ingredient){
        return ingredientService.checkDependencies(ingredient);
    }
}
