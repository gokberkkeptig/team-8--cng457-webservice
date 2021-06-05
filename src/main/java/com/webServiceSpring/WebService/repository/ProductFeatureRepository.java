package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductFeatureRepository extends JpaRepository<ProductFeature, ProductFeatureKeyHolder> {

}
