package de.fasterfood.fasterfood.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class IngredientController {
    IngredientRepository ingredientRepository;
    IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository, IngredientService ingredientService){
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/api/ingredients")
    public List<Ingredient> listAllIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/api/ingredients/price")
    public List<Ingredient> changeIngredientPrices(@RequestBody Ingredient ingredient){
        ingredientService.changePurchasePrice(ingredient);
        return listAllIngredients();
    }

    @PostMapping("/api/ingredients/stock")
    public List<Ingredient> changeStock(@RequestBody Ingredient ingredient){
        ingredientService.changeStock(ingredient);
        return listAllIngredients();
    }
}
