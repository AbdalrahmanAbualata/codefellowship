package com.example.codefellowship.controller;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
public class ApplicationUserController {
    @Autowired
    AppUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username,
                               String password,
                               String firstName,
                               String lastName,
                               String dateOfBirth,
                               String bio){

        ApplicationUser newUser = new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


    @GetMapping("/Profile")
    public String getUserPage(Model m,Principal principal){
        try {
//            ApplicationUser user = applicationUserRepository.findById(id).get();
            ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
            m.addAttribute("user", user);
            return "Profile";
        }
        catch(Exception e){
            return "Profile";
        }

    }

}
