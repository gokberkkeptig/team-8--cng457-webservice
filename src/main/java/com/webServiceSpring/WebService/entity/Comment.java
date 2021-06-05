package com.webServiceSpring.WebService.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mehmet Bengican Altunsu
 * @author Yusuf Gökberk Keptiğ
 * Comment Class representing a comment of a Product
 * @see Product
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String commentText;
    private int rating;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    @JsonBackReference
    private Product product;

}