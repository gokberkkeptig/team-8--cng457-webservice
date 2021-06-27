package com.webServiceSpring.WebService.controller;


import com.webServiceSpring.WebService.entity.Features;
import com.webServiceSpring.WebService.service.FeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller of Features Entity.
 * Any requests to modify or get Features goes through this controller.
 */

@RestController
public class FeaturesController {
    @Autowired
    private FeaturesService service;

    /**
     * This methods adds a Feature to the Features Table.
     * @param feature A Features Entity.
     * @see Features
     * @return Features
     * HTTP POST Request
     * You can use this method by posting a JSON body that includes variables of the Features class.
     * Example Request: url/addFeature
     */
    @PostMapping("/addFeature")
    public Features addFeatures(@RequestBody Features feature){

        return service.saveFeatures(feature);
    }

    /**
     * This methods gets all Features from the Features table.
     * @return List <Features>
     * HTTP GET Request
     * Example Request: url/getFeatures
     */
    @GetMapping("/getFeatures")
    public List<Features> getFeatures(){
        return service.getFeatures();
    }

    /**
     * This method gets a feature given a feature id.
     * @param id An integer representing the feature id.
     * @return Features
     * HTTP GET Request
     * Example Request: url/getFeature/3
     */
    @GetMapping("/getFeature/{id}")
    public Features getFeature(@PathVariable int id){
        return service.getFeature(id);
    }

    /**
     *  This method deletes a feature given a feature id.
     * @param id An integer representing the feature id.
     * HTTP GET Request
     * Example Request: url/deleteFeatures/3
     */
    @DeleteMapping("/deleteFeatures/{id}")
    public String deleteFeatures(@PathVariable int id){
        return service.deleteFeatures(id);
    }
}
