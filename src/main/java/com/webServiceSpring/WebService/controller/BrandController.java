package com.webServiceSpring.WebService.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller of the Brand Entity.
 * Any requests to modify or get Brand goes through this controller.
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
     * HTTP POST Request
     * You can use this method by posting a JSON body that includes variables of the Brand class.
     * Example Request: url/addBrand
     */
    @PostMapping("/addBrand")
    public Brand addBrand(@RequestBody Brand brand){
        return service.saveBrand(brand);
    }

    /**
     * This methods gets all brands from the brand table.
     * @return List <Brand>
     * @see Brand
     * HTTP GET Request
     * Example Request: url/getBrands
     */
    @GetMapping("/getBrands")
    @JsonIgnoreProperties({"productList"})
    public List<Brand> getBrands(){
        return service.getBrands();
    }

    /**
     * This method deletes a Brand given its name.
     * @param id A String representing the name of the Brand.
     * HTTP DELETE Request
     * Example Request: url/deleteBrand/Samsung
     */
    @DeleteMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable String id){
        return service.deleteBrand(id);
    }
}
