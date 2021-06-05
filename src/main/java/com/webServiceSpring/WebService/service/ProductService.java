package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }


    public Product getProduct(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteProduct(int id){
        repository.deleteById(id);
        return "Product is deleted";
    }
}
