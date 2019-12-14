package de.fasterfood.fasterfood.editMeal;

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

    @GetMapping("api/meals")
    public List<Meal> listAllMeals(){
        return mealRepository.findAll();
    }

    @PostMapping("api/meals/price")
    public List<Meal> changePrice(@RequestBody Meal meal){
        mealService.changeRetailPrice(meal);
        return listAllMeals();
    }

    @PostMapping("/api/meals/addMeal")
    public List<Meal> addMeal(@RequestBody MealDTO meal){
        mealService.addMeal(meal);
        return listAllMeals();
    }


    @PostMapping("/api/meals/deleteMeal")
    public List<Meal> deletedMeal(@RequestBody Meal meal){
        mealService.deleteMeal(meal);
        return listAllMeals();
    }



}
