package edu.bondarmih.memorygame.controller;

import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.repository.GameResultRepository;
import edu.bondarmih.memorygame.repository.UserRepository;
import edu.bondarmih.memorygame.service.GameResultService;
import edu.bondarmih.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created by bondarm on 09.08.16.
 */

@Controller
public class IndexController {

    @RequestMapping("/home")
    public String index() {
        return "index";
    }


}
