package com.webServiceSpring.WebService.controller;


import com.webServiceSpring.WebService.entity.Comment;
import com.webServiceSpring.WebService.entity.JsonHandlers.CommentHandler;
import com.webServiceSpring.WebService.service.CommentService;
import com.webServiceSpring.WebService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller of the Comment Entity.
 */

@RestController
public class CommentController {
    @Autowired
    private CommentService cservice;
    @Autowired
    private ProductService pservice;


    /**
     * This method adds a Comment to a Product
     * @param commentHandler
     * @see CommentHandler
     * @return Comment
     */
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

    /**
     * This methods gets all comments from the comments table.
     * @return List <Comment>
     * @see Comment
     */
    @GetMapping("/getComments")
    public List<Comment> getComments(){
        return cservice.getComments();
    }

    /**
     * This methods gets a comment given a comment id.
     * @param id An integer representing the comment id.
     * @return Comment
     * @see Comment
     */
    @GetMapping("/getComment/{id}")
    public Comment getComment(@PathVariable int id){
        return cservice.getComment(id);
    }

    /**
     * This methods deletes a comment given a comment id.
     * @param id An integer representing the comment id.
     */
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable int id){
        return cservice.deleteComment(id);
    }
}
