package de.fasterfood.fasterfood.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {
    IngredientRepository ingredientRepository;
    IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/price/ingredients")
    public List<Ingredient> listAllIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/price/ingredients")
    public void changeIngredientPrices(List<Ingredient> ingredients, List<Integer> prices){
        for(int i=0; i<ingredients.size(); i++){
            ingredientService.changePurchasePrice(ingredients.get(i), prices.get(i));
        }
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
