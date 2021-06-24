package com.webServiceSpring.WebService.Service;

import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Computer;

import com.webServiceSpring.WebService.entity.JsonHandlers.PhoneHandler;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.repository.ComputerRepository;

import com.webServiceSpring.WebService.service.ComputerService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {

    @InjectMocks
    ComputerService computerService;

    @Mock
    ComputerRepository computerRepository;


    @Test
    void getComputersByBrand(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        Brand b = new Brand("Alienware");
        pr1.setBrand(b);
        pr2.setBrand(b);
        Computer c1 = new Computer(99,"1920x1080");
        Computer c2 = new Computer(100,"1280x720");
        c1.setProduct(pr1);
        c2.setProduct(pr2);
        List<Computer> computers = new ArrayList<>();
        computers.add(c1);
        computers.add(c2);

        when(computerRepository.getComputerByBrand("Alienware")).thenReturn(new ArrayList(computers));
        List<Computer> computerList = computerService.getComputerByBrand("Alienware");
        Assertions.assertEquals(computers.size(),computerList.size());
        Assertions.assertEquals(99,computerList.get(computerList.size()-2).getComputerID());
        Assertions.assertEquals(100,computerList.get(computerList.size()-1).getComputerID());
    }
    @Test
    void getComputersByCriteria(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        Brand b = new Brand("Alienware");
        pr1.setBrand(b);
        pr2.setBrand(b);
        Computer p1 = new Computer(99,"1920x1080");
        Computer p2 = new Computer(100,"1280x720");
        p1.setProduct(pr1);
        p2.setProduct(pr2);
        List<Computer> computers = new ArrayList<>();
        computers.add(p1);
        computers.add(p2);

        when(computerRepository.getComputerByCriteria("Alienware",null,null,null,null,null,null,null)).thenReturn(new ArrayList(computers));
        List<Computer> computerList = computerService.getComputerByCriteria("Alienware",null,null,null,null,null,null,null);
        Assertions.assertEquals(computers.size(),computerList.size());
        Assertions.assertEquals(99,computerList.get(0).getComputerID());
        Assertions.assertEquals(100,computerList.get(computerList.size()-1).getComputerID());
    }


}
