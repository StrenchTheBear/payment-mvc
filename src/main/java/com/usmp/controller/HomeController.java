package com.usmp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/index")
    public String indexAfterLogin(Authentication auth) {
        String username = auth.getName();
        return "redirect:/";
    }

}
