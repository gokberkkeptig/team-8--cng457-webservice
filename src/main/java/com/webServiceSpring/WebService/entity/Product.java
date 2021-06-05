package com.webServiceSpring.WebService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Id;

import java.util.List;
import java.util.Set;

/**
 * @author Mehmet Bengican Altunsu
 * @author Yusuf Gökberk Keptiğ
 *
 * Product Class represents the abstract Product class which is the parent
 * of Computer and Phone Classes
 */
//
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "pid")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private char type;
    private String model;
    private Double price;
    private Double screenSize;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "pid")
    @JsonIgnoreProperties("productFeatures")
    private List<Comment> commentList;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Phone phone;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Computer computer;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("productFeatures")
    private List<ProductFeature> productFeatures;

    @ManyToOne
    @JoinColumn(name="brand_name",nullable = false)
    @JsonBackReference
    private Brand brand;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }
}



