package edu.bondarmih.memorygame.controller;

import edu.bondarmih.memorygame.dto.UserDTO;
import edu.bondarmih.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 11.08.16.
 */

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(name = "userList")
    private List<UserDTO> userList() {
        List<UserDTO> result = userService.findAll();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getUsers() {
        return "users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteUser(@PathVariable(value = "id") Integer userId) {
        userService.delete(userId);
        return true;
    }

}
