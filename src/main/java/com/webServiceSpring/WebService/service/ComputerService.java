package com.webServiceSpring.WebService.service;


import com.webServiceSpring.WebService.entity.Computer;
import com.webServiceSpring.WebService.entity.Phone;
import com.webServiceSpring.WebService.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository repository;

    public Computer saveComputer(Computer Computer){
        return repository.save(Computer);
    }

    public List<Computer> getComputers(){
        return repository.findAll();
    }

    public List<Computer> getComputerByBrand(String brand){
        return repository.getComputerByBrand(brand);
    }

    public List<Computer> getComputerByCriteria(@Param("brandName") String brand,@Param("modelp") String model,@Param("screenSizeP") Double screenSize,@Param("proc") String processor,@Param("mem") Integer memory,@Param("storage") Integer storageCapacity){
        return repository.getComputerByCriteria(brand,model,screenSize,processor,memory,storageCapacity);
    }

    public Computer getComputer(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteComputer(int id){
        repository.deleteById(id);
        return "Computer is deleted";
    }
}
