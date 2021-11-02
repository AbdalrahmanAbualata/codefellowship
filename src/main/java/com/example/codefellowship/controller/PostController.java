package com.example.codefellowship.controller;


import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Models.Post;
import com.example.codefellowship.Repositories.AppUserRepository;
import com.example.codefellowship.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Controller
public class PostController {
    @Autowired
    AppUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;


    @GetMapping("/addPost")
    public String showAddPost(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        return "addPost";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(Principal p, String body) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date timeStamp = new Date();

        ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());
        Post post = new Post(body, dateFormat.format(timeStamp), theUser);
        postRepository.save(post);
        return new RedirectView("/users/" + theUser.getId());
    }
    @GetMapping("/feed")
    public String showFeed(Principal p, Model m) {
        if (p != null) {
            m.addAttribute("username", p.getName());
        }
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        Set<ApplicationUser> followerList = currentUser.getUsersIFollow();
        m.addAttribute("peopleIfollowList", followerList);
        m.addAttribute("username", p.getName());
        return "feed";

    }
}
