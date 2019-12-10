package de.fasterfood.fasterfood.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MealController {

    private MealRepository mealRepository;
    private MealService mealService;

    @Autowired
    public MealController(MealRepository mealRepository, MealService mealService){
        this.mealRepository = mealRepository;
        this.mealService = mealService;
    }

    @GetMapping("price/meals")
    public List<Meal> listAllMeals(){
        return mealRepository.findAll();
    }

    @PostMapping("price/meals")
    public void changePrices(List<Meal> meals, List<Integer> prices){
        for (int i=0; i<meals.size(); i++){
            mealService.changeRetailPrice(meals.get(i), prices.get(i));
        }
    }

}
