package edu.bondarmih.memorygame.repository;

import edu.bondarmih.memorygame.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bondarm on 05.08.16.
 */


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    void deleteById(Integer id);


}
