package de.fasterfood.fasterfood.ingredient;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    List<Ingredient> findAll();

}
