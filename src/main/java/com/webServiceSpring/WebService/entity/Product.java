package com.webServiceSpring.WebService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Id;

import java.util.List;

/**
 * @author Mehmet Bengican Altunsu
 * @author Yusuf Gökberk Keptiğ
 *
 * Product Class represents the abstract Product class which is the parent
 * of Computer and Phone Classes
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String model;
    private Double screenSize;
    private Double price;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "pid")
    private List<Comment> commentList;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Phone phone;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Computer computer;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProductDecorator decorator;


}
