package de.fasterfood.fasterfood.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public void changeRetailPrice(Meal meal){
        mealRepository.save(meal);
}

}
