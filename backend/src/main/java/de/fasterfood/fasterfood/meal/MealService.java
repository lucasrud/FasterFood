package de.fasterfood.fasterfood.meal;

import org.springframework.beans.factory.annotation.Autowired;

public class MealService {

    @Autowired
    MealRepository mealRepository;

    public void changePrice(Meal meal, int value){
        meal.setRetailPrice(value);
        mealRepository.save(meal);
    }

}
