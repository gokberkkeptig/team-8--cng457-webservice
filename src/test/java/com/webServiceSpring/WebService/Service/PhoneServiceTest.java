package com.webServiceSpring.WebService.Service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;



import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.repository.PhoneRepository;
import com.webServiceSpring.WebService.service.PhoneService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    @InjectMocks
    PhoneService phoneService;

    @Mock
    PhoneRepository phoneRepository;

    @Test
    void getPhonesByBrand(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        Brand b = new Brand("LG");
        pr1.setBrand(b);
        pr2.setBrand(b);
        Phone p1 = new Phone(99,99);
        Phone p2 = new Phone(100,100);
        p1.setProduct(pr1);
        p2.setProduct(pr2);
        pr1.setPhone(p1);
        pr2.setPhone(p2);
        List<Phone> phones = new ArrayList<>();
        phones.add(p1);
        phones.add(p2);

        when(phoneRepository.getPhoneByBrand("LG")).thenReturn(new ArrayList(phones));
        List<Phone> phoneList = phoneService.getPhoneByBrand("LG");
        Assertions.assertEquals(phones.size(),phoneList.size());
        Assertions.assertEquals(99,phoneList.get(phoneList.size()-2).getPhoneID());
        Assertions.assertEquals(100,phoneList.get(phoneList.size()-1).getPhoneID());
    }

    @Test
    void getPhonesByCriteria(){
        Product pr1 = new Product();
        Product pr2 = new Product();
        Brand b = new Brand("LG");
        pr1.setBrand(b);
        pr2.setBrand(b);
        Phone p1 = new Phone(99,99);
        Phone p2 = new Phone(100,100);
        p1.setProduct(pr1);
        p2.setProduct(pr2);
        List<Phone> phones = new ArrayList<>();
        phones.add(p1);
        phones.add(p2);

        when(phoneRepository.getPhoneByCriteria("LG",null,null,null,null)).thenReturn(new ArrayList(phones));
        List<Phone> phoneList = phoneService.getPhoneByCriteria("LG",null,null,null,null);
        Assertions.assertEquals(phones.size(),phoneList.size());
        Assertions.assertEquals(99,phoneList.get(0).getPhoneID());
        Assertions.assertEquals(100,phoneList.get(phoneList.size()-1).getPhoneID());
    }

}
