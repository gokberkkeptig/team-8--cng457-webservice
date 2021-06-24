package com.webServiceSpring.WebService.entity.connectors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductFeatureKeyHolder implements Serializable {

    private int product_id;
    private int feature_id;

}
