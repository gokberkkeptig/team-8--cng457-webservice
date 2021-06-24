package com.webServiceSpring.WebService.Controller;
import com.webServiceSpring.WebService.WebServiceApplication;
import com.webServiceSpring.WebService.controller.ComputerController;
import com.webServiceSpring.WebService.controller.PhoneController;
import com.webServiceSpring.WebService.entity.*;
import com.webServiceSpring.WebService.entity.JsonHandlers.ComputerHandler;
import com.webServiceSpring.WebService.entity.JsonHandlers.PhoneHandler;
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

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes =  WebServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComputerControllerTest {

    @InjectMocks
    ComputerController computerController;

    @Mock
    ComputerService computerService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getComputerByBrand(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        List<ProductFeature> pf = new ArrayList<>();
        Brand b = new Brand("Alienware");
        pr1.setBrand(b);
        pr2.setBrand(b);
        pr1.setProductFeatures(pf);
        pr2.setProductFeatures(pf);
        Computer p1 = new Computer(99,"1920x1080","i9 9750H",16,1);
        Computer p2 = new Computer(100,"1280x720","i9 9750H",16,1);
        p1.setProduct(pr1);
        p2.setProduct(pr2);

        List<Computer> computers = new ArrayList<>();
        computers.add(p1);
        computers.add(p2);

        when(computerService.getComputerByBrand("Alienware")).thenReturn(new ArrayList(computers));
        List<ComputerHandler> computerList = computerController.getComputerByBrand("Alienware");
        Assertions.assertEquals(computers.size(),computerList.size());
        Assertions.assertEquals(99,computerList.get(computerList.size()-2).getComputerID());
        Assertions.assertEquals(100,computerList.get(computerList.size()-1).getComputerID());
    }

    @Test
    void getComputerByBrand2(){
        assertTrue(
                this.restTemplate.getForObject("http://localhost:" + port + "/getComputerByBrand/Monster", ComputerHandler[].class)[0].getComputerID() == 13);
    }
    @Test
    void getComputerByCriteria(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        List<ProductFeature> pf = new ArrayList<>();
        List<Comment> com = new ArrayList<>();
        Brand b = new Brand("Alienware");
        pr1.setBrand(b);
        pr2.setBrand(b);
        pr1.setProductFeatures(pf);
        pr2.setProductFeatures(pf);
        pr1.setCommentList(com);
        pr2.setCommentList(com);
        Computer p1 = new Computer(99,"1920x1080","i9 9750H",16,1);
        Computer p2 = new Computer(100,"1920x1080","i9 9750H",16,1);
        p1.setProduct(pr1);
        p2.setProduct(pr2);

        List<Computer> computers = new ArrayList<>();
        computers.add(p1);
        computers.add(p2);

        when(computerService.getComputerByCriteria("Alienware",null,null,null,null,null,null,null)).thenReturn(new ArrayList(computers));
        List<ComputerHandler> computerList = computerController.getComputerByCriteria("Alienware",null,null,null,null,null,null,null);
        Assertions.assertEquals(computers.size(),computerList.size());
        Assertions.assertEquals(99,computerList.get(computerList.size()-2).getComputerID());
        Assertions.assertEquals(100,computerList.get(computerList.size()-1).getComputerID());
    }



    @Test
    void getComputerByCriteria2(){
        assertTrue(
                this.restTemplate.getForObject("http://localhost:" + port + "/getComputerByCriteria?brandName=Monster", ComputerHandler[].class)[0].getComputerID() == 13);


    }




}


