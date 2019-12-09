package de.fasterfood.fasterfood.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MealController {

    private MealRepository mealRepository;

    @Autowired
    public MealController(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }


}
