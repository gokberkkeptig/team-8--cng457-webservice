package com.webServiceSpring.WebService.entity.JsonHandlers;

import com.webServiceSpring.WebService.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneHandler {
    private int phoneID;
    private int internalMemory;
    private Double screenSize;
    private String model;
    private Double price;
    private String brandName;
    private List<String> featureList;
    private List<Comment> commentList;
}





