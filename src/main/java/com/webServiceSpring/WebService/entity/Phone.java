package com.webServiceSpring.WebService.entity;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Phone")
public class Phone{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneID;
    private int internalMemory;


   @OneToOne
   @MapsId
   @JoinColumn(name = "product_id", referencedColumnName = "phoneID")
   private Product product;


    public Phone(int phoneID, int internalMemory) {
        this.phoneID = phoneID;
        this.internalMemory = internalMemory;
    }


}

