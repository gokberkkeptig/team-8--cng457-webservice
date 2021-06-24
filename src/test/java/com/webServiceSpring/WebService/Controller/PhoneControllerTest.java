package com.webServiceSpring.WebService.Controller;


import com.webServiceSpring.WebService.WebServiceApplication;
import com.webServiceSpring.WebService.controller.ComputerController;
import com.webServiceSpring.WebService.controller.PhoneController;
import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Comment;
import com.webServiceSpring.WebService.entity.JsonHandlers.PhoneHandler;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.service.ComputerService;
import com.webServiceSpring.WebService.service.PhoneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = WebServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneControllerTest {
    @InjectMocks
    PhoneController phoneController;


    @Mock
    PhoneService phoneservice;


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getPhonesByBrand(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        List<ProductFeature> pf = new ArrayList<>();
        Brand b = new Brand("LG");
        pr1.setBrand(b);
        pr2.setBrand(b);
        pr1.setProductFeatures(pf);
        pr2.setProductFeatures(pf);
        Phone p1 = new Phone(99,99);
        Phone p2 = new Phone(100,100);
        p1.setProduct(pr1);
        p2.setProduct(pr2);

        List<Phone> phones = new ArrayList<>();
        phones.add(p1);
        phones.add(p2);

        when(phoneservice.getPhoneByBrand("LG")).thenReturn(new ArrayList(phones));
        List<PhoneHandler> phoneList = phoneController.getPhoneByBrand("LG");
        Assertions.assertEquals(phones.size(),phoneList.size());
        Assertions.assertEquals(99,phoneList.get(phoneList.size()-2).getPhoneID());
        Assertions.assertEquals(100,phoneList.get(phoneList.size()-1).getPhoneID());
    }

    @Test
    void getPhonesByBrand2(){
        assertTrue(
                this.restTemplate.getForObject("http://localhost:" + port + "/getPhoneByBrand/Apple", PhoneHandler[].class)[0].getPhoneID() == 20);
    }
    @Test
    void getPhonesByCriteria(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        List<ProductFeature> pf = new ArrayList<>();
        List<Comment> com = new ArrayList<>();
        Brand b = new Brand("LG");
        pr1.setBrand(b);
        pr2.setBrand(b);
        pr1.setProductFeatures(pf);
        pr2.setProductFeatures(pf);
        pr1.setCommentList(com);
        pr2.setCommentList(com);
        Phone p1 = new Phone(99,99);
        Phone p2 = new Phone(100,100);
        p1.setProduct(pr1);
        p2.setProduct(pr2);

        List<Phone> phones = new ArrayList<>();
        phones.add(p1);
        phones.add(p2);

        when(phoneservice.getPhoneByCriteria("LG",null,null,null,null)).thenReturn(new ArrayList(phones));
        List<PhoneHandler> phoneList = phoneController.getPhoneByCriteria("LG",null,null,null,null);
        Assertions.assertEquals(phones.size(),phoneList.size());
        Assertions.assertEquals(99,phoneList.get(phoneList.size()-2).getPhoneID());
        Assertions.assertEquals(100,phoneList.get(phoneList.size()-1).getPhoneID());
    }



    @Test
    void getPhonesByCriteria2(){
        assertTrue(
                this.restTemplate.getForObject("http://localhost:" + port + "/getPhoneByCriteria?brandName=Samsung", PhoneHandler[].class)[0].getPhoneID() == 19);


    }

}
