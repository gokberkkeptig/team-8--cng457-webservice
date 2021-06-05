package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository repository;

    public Phone savePhone(Phone Phone){
        return repository.save(Phone);
    }

    public List<Phone> getPhones(){
        return repository.findAll();
    }

    public Phone getPhone(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Phone> getPhoneByBrand(String brand){
        return repository.getPhoneByBrand(brand);
    }
    public String deletePhone(int id){
        repository.deleteById(id);
        return "Phone is deleted";
    }
    public List<Phone> getPhoneByCriteria(@Param("brandName") String brand, @Param("model") String model, @Param("screenSizeP") Double screenSize, @Param("internalMem") Integer internalMemory){
        return repository.getPhoneByCriteria(brand,model,screenSize,internalMemory);
    }
}
