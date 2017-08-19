package io.iamkyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kj Nam
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "index";
    }
}
