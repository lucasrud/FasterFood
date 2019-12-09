package de.fasterfood.fasterfood.order;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

}
