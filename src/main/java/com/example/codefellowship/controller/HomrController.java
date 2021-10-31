package com.example.codefellowship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
@Controller
public class HomrController {

    @GetMapping("/")
    public String getHome(Principal principal, Model m) {
        if (principal != null) {
            m.addAttribute("username", principal.getName());
        }
        return "index";
    }
}
