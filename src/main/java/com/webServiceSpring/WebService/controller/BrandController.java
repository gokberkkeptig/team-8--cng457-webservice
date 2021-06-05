package com.webServiceSpring.WebService.controller;


import com.webServiceSpring.WebService.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandService service;

    @PostMapping("/addBrand")
    public Brand addBrand(@RequestBody Brand brand){
        return service.saveBrand(brand);
    }

    @GetMapping("/getBrands")
    public List<Brand> getBrands(){
        return service.getBrands();
    }

    @DeleteMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable String id){
        return service.deleteBrand(id);
    }
}
