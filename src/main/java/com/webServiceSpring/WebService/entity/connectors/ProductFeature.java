package com.webServiceSpring.WebService.entity.connectors;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.webServiceSpring.WebService.entity.Features;
import com.webServiceSpring.WebService.entity.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_feature")
public class ProductFeature {

    @EmbeddedId
    @JsonIgnoreProperties("key")
    ProductFeatureKeyHolder key;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",insertable = false,updatable = false)
    @JsonBackReference
    Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_id",insertable = false,updatable = false)
    @JsonBackReference
    Features feature;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Features getFeature() {
        return feature;
    }

    public void setFeature(Features feature) {
        this.feature = feature;
    }

    public ProductFeatureKeyHolder getKey() {
        return key;
    }

    public void setKey(ProductFeatureKeyHolder key) {
        this.key = key;
    }
}