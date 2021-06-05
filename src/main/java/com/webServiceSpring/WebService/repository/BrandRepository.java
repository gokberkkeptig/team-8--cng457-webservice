package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,String> {
}
