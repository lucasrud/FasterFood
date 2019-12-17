package de.fasterfood.fasterfood.process;


import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProcessRepository extends CrudRepository<Process, Integer> {

    List<Process> findAll();
    List<Process> findAllByOrderDate(String localDate);

}
