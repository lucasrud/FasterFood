package de.fasterfood.fasterfood.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public void changeRetailPrice(Meal meal, int value){
        meal.setRetailPrice(value);
        mealRepository.save(meal);

    }

    public void addMeal(MealDTO meal) {

            Meal newMeal = new Meal();
            newMeal.setName(meal.getName());
            newMeal.setRetailPrice(meal.getPrice());
            mealRepository.save(newMeal);

    }
}
