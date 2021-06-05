package com.webServiceSpring.WebService.controller;

import com.webServiceSpring.WebService.entity.*;
import com.webServiceSpring.WebService.entity.JsonHandlers.ComputerHandler;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import com.webServiceSpring.WebService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ComputerController {
    @Autowired
    private ComputerService cservice;
    @Autowired
    private BrandService bservice;
    @Autowired
    private ProductService pservice;
    @Autowired
    private FeaturesService fservice;
    @Autowired
    private ProductFeatureService pfservice;

    @PostMapping("/addComputer")
    public Computer addComputer(@RequestBody ComputerHandler computerHandler){
        Computer c = new Computer();
        c.setMemory(computerHandler.getMemory());
        c.setProcessor(computerHandler.getProcessor());
        c.setScreenResolution(computerHandler.getScreenResolution());
        c.setStorageCapacity(computerHandler.getStorageCapacity());

        Product p = new Product();
        p.setScreenSize(computerHandler.getScreenSize());
        p.setPrice(computerHandler.getPrice());
        p.setModel(computerHandler.getModel());
        p.setType('C');

        Brand b = bservice.getBrand(computerHandler.getBrandName());
        p.setBrand(b);
        b.addProduct(p);

        pservice.saveProduct(p);

        c.setProduct(p);
        p.setComputer(c);
        int size=computerHandler.getFeatureList().size();

        ProductFeature pf = new ProductFeature();
        for(int i=0;i<size;i++){
            pf.setProduct(p);
            Features feature = fservice.getFeatureByName(computerHandler.getFeatureList().get(i));
            pf.setFeature(feature);
            pf.setKey(new ProductFeatureKeyHolder(p.getPid(),feature.getFID()));

            pfservice.saveProductFeature(pf);
        }

        return  c;
    }

    @GetMapping("/getComputerByBrand/{brand}")
    public List<ComputerHandler> getComputerByBrand(@PathVariable String brand){
        int size = cservice.getComputerByBrand(brand).size();
        int featureSize;
        List<ComputerHandler> ch = new java.util.ArrayList<>(Collections.emptyList());
        Computer tempComp;
        ComputerHandler temp;
        for(int i=0;i<size;i++){
            temp = new ComputerHandler();
            tempComp = cservice.getComputerByBrand(brand).get(i);
            temp.setComputerID(tempComp.getComputerID());
            temp.setBrandName(tempComp.getProduct().getBrand().getName());
            temp.setMemory(tempComp.getMemory());
            temp.setModel(tempComp.getProduct().getModel());
            temp.setPrice(tempComp.getProduct().getPrice());
            temp.setProcessor(tempComp.getProcessor());
            temp.setScreenSize(tempComp.getProduct().getScreenSize());
            temp.setScreenResolution(tempComp.getScreenResolution());
            temp.setStorageCapacity(tempComp.getStorageCapacity());
            featureSize=tempComp.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new java.util.ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempComp.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ch.add(temp);
        }
        return ch;
    }
    @GetMapping("/getComputerByCriteria")
    List<ComputerHandler> getComputerByCriteria(@RequestParam(value = "brandName",required = false) String brand,@RequestParam(value = "model",required = false) String model,@RequestParam(value = "screenSize",required = false) Double screenSize,@RequestParam(value = "proc",required = false) String processor,@RequestParam(value = "memory",required = false) Integer memory,@RequestParam(value = "storage",required = false) Integer storageCapacity) {
        int size = cservice.getComputerByCriteria(brand,model,screenSize,processor,memory,storageCapacity).size();
        int featureSize;
        List<ComputerHandler> ch = new java.util.ArrayList<>(Collections.emptyList());
        Computer tempComp;
        ComputerHandler temp;
        for(int i=0;i<size;i++){
             temp = new ComputerHandler();
            tempComp = cservice.getComputerByCriteria(brand,model,screenSize,processor,memory,storageCapacity).get(i);
            temp.setComputerID(tempComp.getComputerID());
            temp.setBrandName(tempComp.getProduct().getBrand().getName());
            temp.setMemory(tempComp.getMemory());
            temp.setModel(tempComp.getProduct().getModel());
            temp.setPrice(tempComp.getProduct().getPrice());
            temp.setProcessor(tempComp.getProcessor());
            temp.setScreenSize(tempComp.getProduct().getScreenSize());
            temp.setScreenResolution(tempComp.getScreenResolution());
            temp.setStorageCapacity(tempComp.getStorageCapacity());
            featureSize=tempComp.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new java.util.ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempComp.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ch.add(temp);
        }
        return ch;
    }
    @GetMapping("/getComputers")
    public List<Computer> getComputers(){
        return cservice.getComputers();
    }
    @GetMapping("/getComputer/{id}")
    public Computer getComputer(@PathVariable int id){
        return cservice.getComputer(id);
    }
    @DeleteMapping("/deleteComputer/{id}")
    public String deleteComputer(@PathVariable int id){
        return cservice.deleteComputer(id);
    }
}
