package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Comment;
import com.webServiceSpring.WebService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public Comment saveComment(Comment Comment){
        return repository.save(Comment);
    }

    public List<Comment> getComments(){
        return repository.findAll();
    }

    public Comment getComment(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteComment(int id){
        repository.deleteById(id);
        return "Comment is deleted";
    }
}
