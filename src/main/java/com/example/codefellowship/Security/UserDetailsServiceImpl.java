package com.example.codefellowship.Security;

import com.example.codefellowship.Models.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user= appUserRepository.findByUsername(username);
        if(user == null){
            System.out.println("Username: "+username+"Is not found");
            throw new UsernameNotFoundException("Username: "+username+"Doesn't exist!");
        }
        else {
            System.out.println("User "+ username + " found");
            return user;
        }
    }
}
