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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is the controller of Computer Entity.
 */

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


    /**
     * This methods adds a Computer to the Computer table.
     * @param computerHandler
     * @see ComputerHandler
     * @see Computer
     */
    @PostMapping("/addComputer")
    public void addComputer(@RequestBody ComputerHandler computerHandler){

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


    }


    /**
     * This method gets a computer from a particular brand.
     * @param brand A string representing the brandname.
     * @see Brand
     * @return List <ComputeHandler>
     * @see ComputerHandler
     */
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

    /**
     *
     * @param brand A String representing the brand name.
     * @param model A String representing the model of the product.
     * @param processor A String representing the processor of the product.
     * @param memory An integer representing the memory of the computer.
     * @param storageCapacity An integer representing the storage capacity of the computer.
     * @param screenSizeString A String representing the screen size type of the computer.(Large Screen)
     * @param storageString A String representing the storage capacity type of the computer.(Large Storage)
     * @param memString A String representing the memory type of the computer.(Large Memory)
     * @return List <ComputerHandler>
     * @see ComputerHandler
     */
    @GetMapping("/getComputerByCriteria")
    public List<ComputerHandler> getComputerByCriteria(@RequestParam(value = "brandName",required = false) String brand,@RequestParam(value = "model",required = false) String model,@RequestParam(value = "proc",required = false) String processor,@RequestParam(value = "memory",required = false) Integer memory,@RequestParam(value = "storage",required = false) Integer storageCapacity,@RequestParam(value = "screenSizeString",required = false) String screenSizeString,@RequestParam(value = "storageString",required = false) String storageString,@RequestParam(value = "memString",required = false) String memString) {

        int size = cservice.getComputerByCriteria(brand,model,processor,memory,storageCapacity,screenSizeString,storageString,memString).size();
        int featureSize;
        List<ComputerHandler> ch = new java.util.ArrayList<>(Collections.emptyList());
        Computer tempComp;
        ComputerHandler temp;
        for(int i=0;i<size;i++){
             temp = new ComputerHandler();
            tempComp = cservice.getComputerByCriteria(brand,model,processor,memory,storageCapacity,screenSizeString,storageString,memString).get(i);
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
            int commentSize = tempComp.getProduct().getCommentList().size();
            List<Comment> tempCommentList = new java.util.ArrayList<>(Collections.emptyList());
            for(int j=0;j<commentSize;j++){
                tempCommentList.add(tempComp.getProduct().getCommentList().get(j));

            }
            temp.setCommentList(tempCommentList);


            ch.add(temp);
        }

        return ch;
    }

    /**
     * This methods gets all computer from the computer table.
     * @return List <Computer>
     * @see Computer
     */
    @GetMapping("/getComputers")
    public List<Computer> getComputers(){

        List<Computer> c = cservice.getComputers();

        return c;
    }

    /**
     * This method gets a computer given a computer id.
     * @param id An integer representing the id of the computer.
     * @return Computer
     * @see Computer
     */
    @GetMapping("/getComputer/{id}")
    public Computer getComputer(@PathVariable int id){

        Computer c = cservice.getComputer(id);


        return c;
    }

    /**
     * This method deletes a computer given a computer id.
     * @param id An integer representing the id of the computer.
     */
    @DeleteMapping("/deleteComputer/{id}")
    public void deleteComputer(@PathVariable int id){

        cservice.deleteComputer(id);

    }
}
