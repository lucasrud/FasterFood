package de.fasterfood.fasterfood.editMeal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    List<Meal> findAll();
    Meal findById(int id);
    Meal findByName(String name);

}
