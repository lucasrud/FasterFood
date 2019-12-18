package de.fasterfood.fasterfood.user;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    List<UserEntity> findAll();
    Optional<UserEntity> findById(Integer id);
    Optional<UserEntity> findByUsername(String username);
}
