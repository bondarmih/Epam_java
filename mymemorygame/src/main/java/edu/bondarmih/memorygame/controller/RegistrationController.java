package edu.bondarmih.memorygame.controller;

import edu.bondarmih.memorygame.entity.Role;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.service.RoleService;
import edu.bondarmih.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by bondarm on 11.08.16.
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    private final RoleService roleService;

    private Logger logger = Logger.getLogger("registrationController");

    @Autowired
    public RegistrationController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(User user) {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "register";
        }

        if (userService.findByName(user.getName()) != null) {
            return "register";
        }

        Role userRole = roleService.findByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(userRole);

        user.setRoles(roleList);
        userService.save(user);
        logger.info("user "+ user.getName() + " has registered");
        return "redirect:/home";
    }
}