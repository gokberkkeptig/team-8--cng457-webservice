package com.webServiceSpring.WebService.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Computer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "computerID")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int computerID;
    private String screenResolution;
    private String processor;
    private Integer memory;
    private Integer storageCapacity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}
