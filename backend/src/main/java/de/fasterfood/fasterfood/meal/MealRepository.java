package de.fasterfood.fasterfood.meal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    List<Meal> findAll();
    Meal findById(int id);

}
