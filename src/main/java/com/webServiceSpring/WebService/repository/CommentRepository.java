package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
