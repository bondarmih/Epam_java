package edu.bondarmih.memorygame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
