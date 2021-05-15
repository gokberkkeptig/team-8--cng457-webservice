package com.webServiceSpring.WebService.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneID;
    private int internalMemory;

//    @OneToOne(mappedBy = "Product", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private ProductDecorator decorator;


//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "phone_id", referencedColumnName = "phoneID")
//    private Product product;


}

