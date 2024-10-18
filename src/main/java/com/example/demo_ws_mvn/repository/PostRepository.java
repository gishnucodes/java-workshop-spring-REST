package com.example.demo_ws_mvn.repository;

import com.example.demo_ws_mvn.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAll();
}
