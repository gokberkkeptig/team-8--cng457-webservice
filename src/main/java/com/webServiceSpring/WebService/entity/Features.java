package com.webServiceSpring.WebService.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FeaturesTable")
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fID;
    @Column(name = "feature_name")
    private String feature_name;



    @OneToMany(mappedBy = "feature")
    @JsonIgnoreProperties("productFeatures")
    private List<ProductFeature> productFeatures;


}
