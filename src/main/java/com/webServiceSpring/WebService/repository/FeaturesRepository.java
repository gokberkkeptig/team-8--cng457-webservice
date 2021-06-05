package com.webServiceSpring.WebService.repository;

import com.webServiceSpring.WebService.entity.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeaturesRepository extends JpaRepository<Features,Integer> {

    @Query("SELECT f from Features f where f.feature_name = ?1")
    Features getFeatureByName(String name);
}
