package com.webServiceSpring.WebService.entity.JsonHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerHandler {

    private int computerID;
    private String screenResolution;
    private Double screenSize;
    private String processor;
    private int memory;
    private int storageCapacity;
    private String model;
    private Double price;
    private String brandName;
    private List<String> featureList;

}





