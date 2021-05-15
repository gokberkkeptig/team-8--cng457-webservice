package com.webServiceSpring.WebService.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int computerID;
    private String screenResolution;
    private String processor;
    private int memory;
    private int storageCapacity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "computer_id", referencedColumnName = "computerID")
    private Product product;
}
