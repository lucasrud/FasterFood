package de.fasterfood.fasterfood.security;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Iterable<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
}
