package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository repository;

    private static Lock lock = new ReentrantLock(true);

    public Computer saveComputer(Computer Computer){
        lock.lock();
        Computer c = repository.save(Computer);
        lock.unlock();
        return c;
    }

    public List<Computer> getComputers(){

        lock.lock();
        List<Computer> c = repository.findAll();
        lock.unlock();
        return c;
    }

    public List<Computer> getComputerByBrand(String brand){

        lock.lock();
        List<Computer> c = repository.getComputerByBrand(brand);
        lock.unlock();
        return c;
    }

    public List<Computer> getComputerByCriteria(@Param("brandName") String brand,@Param("modelp") String model,@Param("proc") String processor,@Param("mem") Integer memory,@Param("storage") Integer storageCapacity,@Param("screenSizeS") String screenSizeString,@Param("storageString") String storageString,@Param("memString") String memString){
        lock.lock();

        if(screenSizeString!=null) {
            if (screenSizeString.equals("largeScreen"))
                screenSizeString = ">";
            else
                screenSizeString = "<";
        }
        if(storageString!=null) {
            if (storageString.equals("largeStorage"))
                storageString = ">";
            else
                storageString = "<";
        }
        if(memString!=null) {
            if (memString.equals("largeMemory"))
                memString = ">";
            else
                memString = "<";
        }
        List<Computer> c = repository.getComputerByCriteria(brand,model,processor,memory,storageCapacity,screenSizeString,storageString,memString);
        lock.unlock();


        return c;
    }

    public Computer getComputer(int id){

        lock.lock();
        Computer c = repository.findById(id).orElse(null);
        lock.unlock();
        return c;
    }

    public String deleteComputer(int id){
        lock.lock();
        repository.deleteById(id);
        lock.unlock();
        return "Computer is deleted";
    }
}
