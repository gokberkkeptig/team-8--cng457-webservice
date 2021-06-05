package com.webServiceSpring.WebService.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Features;
import com.webServiceSpring.WebService.entity.JsonHandlers.ProductHandler;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import com.webServiceSpring.WebService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private BrandService bservice;
    @Autowired
    private FeaturesService fservice;
    @Autowired
    private ProductFeatureService pfservice;



    @PostMapping("/addProduct")
    @JsonIgnoreProperties({"productList"})
    public Product addProduct(@RequestBody ProductHandler productHandler){
        Product p = new Product();
        p.setType(productHandler.getType());
        p.setModel(productHandler.getModel());
        p.setPrice(productHandler.getPrice());
        p.setScreenSize(productHandler.getScreenSize());
        Brand b = bservice.getBrand(productHandler.getBrandName());
        p.setBrand(b);
        b.addProduct(p);
        service.saveProduct(p);
        int size=productHandler.getFeatureList().size();

        ProductFeature pf = new ProductFeature();
        for(int i=0;i<size;i++){
            pf.setProduct(p);
            Features feature = fservice.getFeatureByName(productHandler.getFeatureList().get(i));
            pf.setFeature(feature);
            pf.setKey(new ProductFeatureKeyHolder(p.getPid(),feature.getFID()));

            pfservice.saveProductFeature(pf);
        }

        return p;
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return service.getProducts();
    }
    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProduct(id);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }
}
