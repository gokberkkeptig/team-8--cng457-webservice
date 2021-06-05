package com.webServiceSpring.WebService.controller;


import com.webServiceSpring.WebService.entity.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeaturesController {
    @Autowired
    private FeaturesService service;

    @PostMapping("/addFeature")
    public Features addFeatures(@RequestBody Features feature){

        return service.saveFeatures(feature);
    }

    @GetMapping("/getFeatures")
    public List<Features> getFeatures(){
        return service.getFeatures();
    }
    @GetMapping("/getFeature/{id}")
    public Features getFeature(@PathVariable int id){
        return service.getFeature(id);
    }
    @DeleteMapping("/deleteFeatures/{id}")
    public String deleteFeatures(@PathVariable int id){
        return service.deleteFeatures(id);
    }
}
