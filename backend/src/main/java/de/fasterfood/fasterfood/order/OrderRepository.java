package de.fasterfood.fasterfood.order;

import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

}
