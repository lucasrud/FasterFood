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

    @GetMapping("/api/price/ingredients")  // KONSOLIDIEREN AK
    public List<Ingredient> listAllIngredients(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/price/ingredients")
    public void changeIngredientPrices(List<Ingredient> ingredients, List<Integer> prices){
        for(int i=0; i<ingredients.size(); i++){
            ingredientService.changePurchasePrice(ingredients.get(i), prices.get(i));
        }
    }

    @GetMapping("/api/stock/ingredients")    // KONSOLIDIEREN AK
    public List<Ingredient> fetchStockIngredient(){
        return ingredientRepository.findAll();
    }

    @PostMapping("/api/stock/ingredients")
<<<<<<< HEAD
    public List<Ingredient> changeStock(Ingredient ingredient){

=======
    public List<Ingredient> changeStock(@RequestBody Ingredient ingredient){
        System.out.println(ingredient.getStock() + "blaa");
>>>>>>> bac7c62ec6159adb325875ab168133949b8dc6c9
        ingredientService.changeStock(ingredient);
        return fetchStockIngredient();
    }
}
