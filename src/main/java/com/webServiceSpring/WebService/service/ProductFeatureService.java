package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import com.webServiceSpring.WebService.repository.ProductFeatureRepository;
import com.webServiceSpring.WebService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFeatureService {
    @Autowired
    private ProductFeatureRepository repository;

    public ProductFeature saveProductFeature(ProductFeature pf){
        return repository.save(pf);
    }

    public List<ProductFeature> getProductFeatures(){
        return repository.findAll();
    }


    public ProductFeature getProductFeature(ProductFeatureKeyHolder id){
        return repository.findById(id).orElse(null);
    }

    public String deleteProductFeature(ProductFeatureKeyHolder id){
        repository.deleteById(id);
        return "Product is deleted";
    }
}
