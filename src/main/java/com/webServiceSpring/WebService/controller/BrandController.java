package com.webServiceSpring.WebService.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller of the Brand Entity.
 */

@RestController
public class BrandController {
    @Autowired
    private BrandService service;


    /**
     * This method adds a Brand to the brand table.
     * @param brand
     * @see Brand
     * @return Brand
     */
    @PostMapping("/addBrand")
    public Brand addBrand(@RequestBody Brand brand){
        return service.saveBrand(brand);
    }

    /**
     * This methods gets all brands from the brand table.
     * @return List <Brand>
     * @see Brand
     */
    @GetMapping("/getBrands")
    @JsonIgnoreProperties({"productList"})
    public List<Brand> getBrands(){
        return service.getBrands();
    }

    /**
     * This method deletes a Brand given its name.
     * @param id A String representing the name of the Brand.
     */
    @DeleteMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable String id){
        return service.deleteBrand(id);
    }
}
