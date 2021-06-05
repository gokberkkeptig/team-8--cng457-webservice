package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Features;
import com.webServiceSpring.WebService.repository.FeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeaturesService {
    @Autowired
    private FeaturesRepository repository;

    public Features saveFeatures(Features feature){
        return repository.save(feature);
    }

    public List<Features> getFeatures(){
        return repository.findAll();
    }


    public Features getFeature(int id){

        return repository.findById(id).orElse(null);
    }

    public String deleteFeatures(int id){
        repository.deleteById(id);
        return "Features is deleted";
    }
    public Features getFeatureByName(String name){
        return repository.getFeatureByName(name);
    }

}
