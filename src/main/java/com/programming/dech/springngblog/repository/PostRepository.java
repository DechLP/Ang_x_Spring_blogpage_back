package com.programming.dech.springngblog.repository;

import com.programming.dech.springngblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
