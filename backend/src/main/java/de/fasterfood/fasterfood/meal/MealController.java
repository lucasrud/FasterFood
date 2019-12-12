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
    public MealController (MealRepository mealRepository, MealService mealService){
        this.mealRepository=mealRepository;
        this.mealService = mealService;
    }

    @GetMapping("api/meals")
    public List<Meal> listAllMeals(){
        return mealRepository.findAll();
    }

    @PostMapping("api/meals/price")
    public List<Meal> changePrice(@RequestBody Meal meal){
        mealService.changeRetailPrice(meal);
        return listAllMeals();
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
