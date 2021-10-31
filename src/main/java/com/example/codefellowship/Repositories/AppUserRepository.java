package com.example.codefellowship.Repositories;

import com.example.codefellowship.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
