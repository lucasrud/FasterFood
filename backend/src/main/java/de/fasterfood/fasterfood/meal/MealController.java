package de.fasterfood.fasterfood.meal;

import de.fasterfood.fasterfood.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MealController {

    private MealRepository mealRepository;
    private MealService mealService;

    @Autowired
    public MealController (MealRepository mealRepository, MealService mealService){
        this.mealRepository=mealRepository;
        this.mealService = mealService;
    }

    @GetMapping("/api/fasterfood/allMeals")
    public List<Meal> listOfItems () {
        return mealRepository.findAll();
    }


    @PostMapping("price/meals")
    public void changePrices(List<Meal> meals, List<Integer> prices){
        for (int i=0; i<meals.size(); i++){
            mealService.changeRetailPrice(meals.get(i), prices.get(i));
        }
    }

    @PostMapping("/api/fasterfood/addMeal")
    public List<Meal> addMeal(@RequestBody MealDTO meal){
        mealService.addMeal(meal);
        return listOfItems();
    }


    @PostMapping("/api/fasterfood/deleteMeal")
    public List<Meal> deletedMeal(@RequestBody Meal meal){
        mealService.deleteMeal(meal);
        return listOfItems();
    }



}
