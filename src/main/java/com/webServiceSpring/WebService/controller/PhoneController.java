package com.webServiceSpring.WebService.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webServiceSpring.WebService.entity.*;
import com.webServiceSpring.WebService.entity.JsonHandlers.ComputerHandler;
import com.webServiceSpring.WebService.entity.JsonHandlers.PhoneHandler;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import com.webServiceSpring.WebService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@RestController
public class PhoneController {
    @Autowired
    private PhoneService phservice;
    @Autowired
    private BrandService bservice;
    @Autowired
    private ProductService pservice;
    @Autowired
    private FeaturesService fservice;
    @Autowired
    private ProductFeatureService pfservice;

    @PostMapping("/addPhone")
    public Phone addPhone(@RequestBody PhoneHandler phoneHandler){
        Phone ph = new Phone();
        ph.setInternalMemory(phoneHandler.getInternalMemory());

        Product p = new Product();
        p.setScreenSize(phoneHandler.getScreenSize());
        p.setPrice(phoneHandler.getPrice());
        p.setModel(phoneHandler.getModel());
        p.setType('P');

        Brand b = bservice.getBrand(phoneHandler.getBrandName());
        p.setBrand(b);
        b.addProduct(p);

        pservice.saveProduct(p);

        ph.setProduct(p);
        p.setPhone(ph);
        int size=phoneHandler.getFeatureList().size();

        ProductFeature pf = new ProductFeature();
        for(int i=0;i<size;i++){
            pf.setProduct(p);
            Features feature = fservice.getFeatureByName(phoneHandler.getFeatureList().get(i));
            pf.setFeature(feature);
            pf.setKey(new ProductFeatureKeyHolder(p.getPid(),feature.getFID()));

            pfservice.saveProductFeature(pf);
        }

        return ph;
    }




    @GetMapping("/getPhoneByBrand/{brand}")
    public List<PhoneHandler> getPhoneByBrand(@PathVariable String brand){
        int size = phservice.getPhoneByBrand(brand).size();
        int featureSize;
        ArrayList<PhoneHandler> ph = new java.util.ArrayList<>();
        Phone tempPhone;
        PhoneHandler temp;
        for(int i=0;i<size;i++){
            temp = new PhoneHandler();
            tempPhone = phservice.getPhoneByBrand(brand).get(i);
            temp.setPhoneID(tempPhone.getPhoneID());
            System.out.println("---------------------PHONEID-----------------------------------------------------------------------------------------------------");
            System.out.println(temp.getPhoneID());
            temp.setBrandName(tempPhone.getProduct().getBrand().getName());
            temp.setInternalMemory(tempPhone.getInternalMemory());
            temp.setModel(tempPhone.getProduct().getModel());
            temp.setPrice(tempPhone.getProduct().getPrice());
            featureSize=tempPhone.getProduct().getProductFeatures().size();
            ArrayList<String> tempFeatureList = new java.util.ArrayList<>();
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ph.add(temp);
        }
        return ph;
    }

    @GetMapping("/getPhoneByCriteria")
    List<PhoneHandler> getPhoneByCriteria(@RequestParam(value = "brandName",required = false) String brand,@RequestParam(value = "model",required = false) String model,@RequestParam(value = "screenSize",required = false) Double screenSize,@RequestParam(value = "internalMem",required = false) Integer internalMemory) {
        int size = phservice.getPhoneByCriteria(brand,model,screenSize,internalMemory).size();
        int featureSize;
        List<PhoneHandler> ph = new java.util.ArrayList<>(Collections.emptyList());
        Phone tempPhone;
        PhoneHandler temp;
        for(int i=0;i<size;i++){
             temp = new PhoneHandler();
            tempPhone = phservice.getPhoneByCriteria(brand,model,screenSize,internalMemory).get(i);
            temp.setPhoneID(tempPhone.getPhoneID());
            temp.setBrandName(tempPhone.getProduct().getBrand().getName());
            temp.setModel(tempPhone.getProduct().getModel());
            temp.setPrice(tempPhone.getProduct().getPrice());
            temp.setScreenSize(tempPhone.getProduct().getScreenSize());
            featureSize=tempPhone.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new java.util.ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ph.add(temp);
        }
        return ph;
    }




    @GetMapping("/getPhones")
    @JsonIgnoreProperties({"productFeatures","product","key"})
    public List<Phone> getPhones(){
        return phservice.getPhones();
    }
    @GetMapping("/getPhone/{id}")
    public Phone getPhone(@PathVariable int id){
        return phservice.getPhone(id);
    }
    @DeleteMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable int id){
        return phservice.deletePhone(id);
    }
}
