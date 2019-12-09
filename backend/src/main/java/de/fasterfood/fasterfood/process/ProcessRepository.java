package de.fasterfood.fasterfood.process;

import de.fasterfood.fasterfood.meal.Meal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProcessRepository extends CrudRepository<Process, Integer> {

    List<Process> findAll();

}
