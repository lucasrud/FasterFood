package de.fasterfood.fasterfood.editMeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public void changeRetailPrice(Meal meal){
        mealRepository.save(meal);

    }

    public void addMeal(MealDTO meal) {

            Meal newMeal = new Meal();
            newMeal.setName(meal.getName());
            newMeal.setRetailPrice(meal.getPrice());
            mealRepository.save(newMeal);

    }

    public void deleteMeal(Meal meal) {
        mealRepository.delete(meal);
    }
}
