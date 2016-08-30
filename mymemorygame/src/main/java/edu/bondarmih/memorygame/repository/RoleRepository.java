package edu.bondarmih.memorygame.repository;

import edu.bondarmih.memorygame.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bondarm on 05.08.16.
 */

public interface RoleRepository  extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
