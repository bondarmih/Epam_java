package edu.bondarmih.memorygame.service;

import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.repository.RoleRepository;
import edu.bondarmih.memorygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bondarm on 11.08.16.
 */
@Service
@Transactional
public class DbInitService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public DbInitService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initDb() {

        /*   Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        User userAdmin = new User();
        userAdmin.setName("admin");
        userAdmin.setPassword("adminpass");
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleAdmin);
        roles.add(roleUser);
        userAdmin.setRoles(roles);
        if (userRepository.findByName("admin") == null) {
            userRepository.save(userAdmin);
            System.out.println("admin added");
        }
*/
        Logger logger = Logger.getLogger("edu.bondarmih.memorygame.service.DbInitService");

        User newUser = userRepository.findByName("admin");
        if (newUser!= null) {
            logger.log(Level.INFO, newUser.toString());
        } else {
            logger.log(Level.INFO, "newUser is null!");
        }
        /* Roles in db
        List<Role> roles = roleRepository.findAll();
        logger.log(Level.INFO,"Roles:___"  +Arrays.toString(roles.toArray()));
        */
    }


}
