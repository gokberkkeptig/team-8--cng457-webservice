package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository repository;

    private static Lock lock = new ReentrantLock(true);

    public Phone savePhone(Phone Phone){

        lock.lock();
        Phone p = repository.save(Phone);
        lock.unlock();

        return p;
    }

    public List<Phone> getPhones(){
        lock.lock();
        List<Phone> p = repository.findAll();
        lock.unlock();
        return p;
    }

    public Phone getPhone(int id){
        lock.lock();
        Phone p = repository.findById(id).orElse(null);
        lock.unlock();
        return p;
    }

    public List<Phone> getPhoneByBrand(String brand){
        lock.lock();
        List<Phone> p = repository.getPhoneByBrand(brand);
        lock.unlock();
        return p;
    }
    public String deletePhone(int id){
        lock.lock();
        repository.deleteById(id);
        lock.unlock();
        return "Phone is deleted";
    }



    public List<Phone> getPhoneByCriteria(@Param("brandName") String brand, @Param("model") String model, @Param("internalMem") Integer internalMemory,@Param("screenSizeS") String screenSizeString,@Param("internalMemS") String internalMemString){
        lock.lock();
        if(screenSizeString!=null) {
            if (screenSizeString.equals("largeScreen"))
                screenSizeString = ">";
            else
                screenSizeString = "<";
        }
        if(internalMemString!=null) {
            if (internalMemString.equals("largeStorage"))
                internalMemString = ">";
            else
                internalMemString = "<";

        }
        List<Phone> p = repository.getPhoneByCriteria(brand,model,internalMemory,screenSizeString,internalMemString);
        lock.unlock();

        return p;
    }
}
