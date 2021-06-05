package com.webServiceSpring.WebService.entity.JsonHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentHandler {
    private String commentText;
    private int rating;
    private int productID;
}

