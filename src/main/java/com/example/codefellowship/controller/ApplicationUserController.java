package com.example.codefellowship.controller;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;


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
              newUser=applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser,null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

// we can use Authentication authentication insted of Principal principal .
    @GetMapping("/Profile")
    public String getUserPage(Model m,Principal principal){
        try {
            ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
            m.addAttribute("user", user);
            return "Profile";
        }
        catch(Exception e){
            return "Profile";
        }
    }

    @GetMapping("/users/{id}")
    public String getUserPage(Principal p,Model m, @PathVariable long id){
        try {
            String username = p.getName();
           ApplicationUser user = applicationUserRepository.findUserById(id);
            m.addAttribute("userForOwner", user);
            m.addAttribute("usernameForVisitor", username);
            return "users";
        }
        catch(Exception e){
            return "users";
        }
    }


}
