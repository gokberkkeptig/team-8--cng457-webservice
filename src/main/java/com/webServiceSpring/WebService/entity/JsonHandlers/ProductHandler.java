package com.webServiceSpring.WebService.entity.JsonHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductHandler {
    private int pid;
    private char type;
    private String model;
    private Double price;
    private String brandName;
    private Double screenSize;
    private List<String> featureList;

}

