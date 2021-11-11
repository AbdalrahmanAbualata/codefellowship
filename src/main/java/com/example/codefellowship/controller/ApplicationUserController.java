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
import java.util.List;


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
    @GetMapping("/users")
    public String getAllUsers( Model model, Principal principal) {
        List<ApplicationUser> allUsers = (List<ApplicationUser>) applicationUserRepository.findAll();
        ApplicationUser me = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("users", allUsers);
        model.addAttribute("me", me);
        return "allUsers";
    }


    @GetMapping("/users/{id}")
    public String getUserPage(Principal principal,Model m, @PathVariable long id){
        try {
            ApplicationUser me = applicationUserRepository.findByUsername(principal.getName());
           ApplicationUser user = applicationUserRepository.findUserById(id);
            m.addAttribute("userForOwner", user);
            m.addAttribute("theVisitor", me);
            return "users";
        }
        catch(Exception e){
            return "users";
        }
    }
    @PostMapping ("/follow")
    public RedirectView followUser(Principal p, long followUser) {
        ApplicationUser follower = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser poster = applicationUserRepository.getOne(followUser);
        follower.followUser(poster);

        applicationUserRepository.save(follower);

        return new RedirectView("/Profile");
    }

    @GetMapping ("/usersIfollow")
    public String usersIfollow(Principal p,Model m) {
        ApplicationUser myObject = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("userForOwner", myObject);
        return "usersIFollowed";
    }
    @GetMapping ("/usersFollowingMe")
    public String usersFollowingMe(Principal p,Model m) {
        ApplicationUser myObject = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("userForOwner", myObject);
        return "userFollowingMe";
    }


}
