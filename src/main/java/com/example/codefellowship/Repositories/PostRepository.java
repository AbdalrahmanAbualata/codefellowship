package com.example.codefellowship.Repositories;

import com.example.codefellowship.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
}
