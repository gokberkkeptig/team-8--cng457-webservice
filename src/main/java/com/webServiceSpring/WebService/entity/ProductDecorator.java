package com.webServiceSpring.WebService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;





@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductDecorator")
public class ProductDecorator {

    @Id
    private int pdID;
    private enum decoratorType{
        AllDayBatteryLife,
        ExtraLongBatteryLife,
        TouchScreen,
        FaceRecognition,
        FingerPrintReader
    }
    @Enumerated(EnumType.STRING)
    private decoratorType decoratorFeature;

    @OneToOne
    @MapsId
    @JoinColumn(name = "decorator_id", referencedColumnName = "pdID")
    private Product product;





}
