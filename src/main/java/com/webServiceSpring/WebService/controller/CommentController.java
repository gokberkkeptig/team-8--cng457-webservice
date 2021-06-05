package com.webServiceSpring.WebService.controller;


import com.webServiceSpring.WebService.entity.Comment;
import com.webServiceSpring.WebService.entity.JsonHandlers.CommentHandler;
import com.webServiceSpring.WebService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService cservice;
    @Autowired
    private ProductService pservice;

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody CommentHandler commentHandler){

        Comment comment = new Comment();
        comment.setCommentText(commentHandler.getCommentText());
        comment.setRating(commentHandler.getRating());
        int pid = commentHandler.getProductID();
        comment.setProduct(pservice.getProduct(pid));
        pservice.getProduct(pid).addComment(comment);
        return cservice.saveComment(comment);
    }

    @GetMapping("/getComments")
    public List<Comment> getComments(){
        return cservice.getComments();
    }
    @GetMapping("/getComment/{id}")
    public Comment getComment(@PathVariable int id){
        return cservice.getComment(id);
    }
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable int id){
        return cservice.deleteComment(id);
    }
}
