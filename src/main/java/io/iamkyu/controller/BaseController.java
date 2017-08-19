package io.iamkyu.controller;

import io.iamkyu.model.FrontUserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kj Nam
 */
@Controller
public class BaseController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal FrontUserDetail frontUserDetail, Model model) {
        model.addAttribute("name", frontUserDetail.getUsername());

        return "home";
    }


}
