package com.webServiceSpring.WebService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Brand")
public class Brand {
    @Id
    private String name;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_name",referencedColumnName = "name")
//    @JsonBackReference
    private List<Product> productList;

    public void addProduct(Product p){
        productList.add(p);
    }

    public Brand(String name) {
        this.name = name;
    }
}
