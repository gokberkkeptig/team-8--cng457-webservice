package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product,Integer> {

}
