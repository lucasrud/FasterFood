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

    @GetMapping("/api/price/ingredients")
    public List<Ingredient> listAllIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/api/price/ingredients")
    public List<Ingredient> changeIngredientPrices(@RequestBody Ingredient ingredient){
        ingredientService.changePurchasePrice(ingredient);
        return listAllIngredients();
    }

    @GetMapping("/stock/ingredients")
    public List<Ingredient> stockIngredient(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/stock/ingredients")
    public void changeStock(List<Ingredient> ingredients, List<Integer> units){
        for(int i=0; i<ingredients.size(); i++){
            ingredientService.changeStock(ingredients.get(i), units.get(i));
        }
    }
}
