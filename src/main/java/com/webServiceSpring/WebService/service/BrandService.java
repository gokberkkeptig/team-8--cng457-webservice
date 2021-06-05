package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository repository;

    public Brand saveBrand(Brand brand){
        return repository.save(brand);
    }

    public List<Brand> getBrands(){
        return repository.findAll();
    }
    public Brand getBrand(String name){
        return repository.findById(name).orElse(null);
    }

    public String deleteBrand(String id){
        repository.deleteById(id);
        return "Brand is deleted";
    }
}
