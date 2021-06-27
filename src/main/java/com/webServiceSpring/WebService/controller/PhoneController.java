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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is the controller of Phone Entity.
 * Any requests to modify or get Phones goes through this controller.
 */

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


    /**
     * This method adds a Phone to the phone table.
     * @param phoneHandler which holds a phone temporarily to handle Json formats.
     * @see PhoneHandler
     * HTTP POST Request
     * You can use this method by posting a JSON body that includes variables of the PhoneHandler class.
     * Example Request: url/addPhone
     */
    @PostMapping("/addPhone")
    public void addPhone(@RequestBody PhoneHandler phoneHandler){

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


    }


    /**
     * This methods gets all phone from a particular brand.
     * @param brand a String representing a Brand.
     * @see Brand
     * @return List <PhoneHandler>
     * @see PhoneHandler
     * HTTP GET Request
     * Example Request: url/getPhoneByBrand/Samsung
     */
    @GetMapping("/getPhoneByBrand/{brand}")
    public List<PhoneHandler> getPhoneByBrand(@PathVariable String brand){

        int size = phservice.getPhoneByBrand(brand).size();
        int featureSize;
        ArrayList<PhoneHandler> ph = new ArrayList<>();
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
            ArrayList<String> tempFeatureList = new ArrayList<>();
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ph.add(temp);
        }

        return ph;

    }

    /**
     *
     * @param brand A string representing brand name
     * @param model A string representing model of the product.
     * @param internalMemory An integer representing the storage capacity of the phone.
     * @param screenSizeString A string representing the screen size type of the phone(Large Screen,Small Screen etc.)
     * @param internalMemString A string representing the internal memory type of the phone(Large Memory, Small Memory etc.)
     * @return List <PhoneHandler>
     * @see PhoneHandler
     * HTTP GET Request
     * Example Request: url/getPhoneByCriteria?brandName=Samsung&screenSize=17.3
     * You can use any attribute of a phone as a parameter for criteria.
     */
    @GetMapping("/getPhoneByCriteria")
    public List<PhoneHandler> getPhoneByCriteria(@RequestParam(value = "brandName",required = false) String brand,@RequestParam(value = "model",required = false) String model,@RequestParam(value = "internalMem",required = false) Integer internalMemory,@RequestParam(value = "screenSizeString",required = false) String screenSizeString,@RequestParam(value = "internalMemString",required = false) String internalMemString) {

        int size = phservice.getPhoneByCriteria(brand,model,internalMemory,screenSizeString,internalMemString).size();
        int featureSize;
        int commentSize;
        List<PhoneHandler> ph = new ArrayList<>(Collections.emptyList());
        Phone tempPhone;
        PhoneHandler temp;
        for(int i=0;i<size;i++){
             temp = new PhoneHandler();
            tempPhone = phservice.getPhoneByCriteria(brand,model,internalMemory,screenSizeString,internalMemString).get(i);
            temp.setPhoneID(tempPhone.getPhoneID());
            temp.setBrandName(tempPhone.getProduct().getBrand().getName());
            temp.setModel(tempPhone.getProduct().getModel());
            temp.setPrice(tempPhone.getProduct().getPrice());
            temp.setScreenSize(tempPhone.getProduct().getScreenSize());
            temp.setInternalMemory(tempPhone.getInternalMemory());
            featureSize=tempPhone.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            commentSize = tempPhone.getProduct().getCommentList().size();
            List<Comment> tempCommentList = new ArrayList<>(Collections.emptyList());
            for(int j=0;j<commentSize;j++){
                tempCommentList.add(tempPhone.getProduct().getCommentList().get(j));

            }
            temp.setCommentList(tempCommentList);

            ph.add(temp);
        }

        return ph;
    }


    /**
     * This methods gets all Phones from the Phone table.
     * @return List <PhoneHandler>
     * @see PhoneHandler
     * HTTP GET Request
     * Example Request: url/getPhones
     */
    @GetMapping("/getPhones")
    @JsonIgnoreProperties({"productFeatures","product","key"})
    public List<PhoneHandler> getPhones(){

        int size = phservice.getPhones().size();
        int featureSize;
        List<PhoneHandler> ph = new ArrayList<>(Collections.emptyList());
        Phone tempPhone;
        PhoneHandler temp;
        for(int i=0;i<size;i++){
            temp = new PhoneHandler();
            tempPhone = phservice.getPhones().get(i);
            temp.setPhoneID(tempPhone.getPhoneID());
            temp.setBrandName(tempPhone.getProduct().getBrand().getName());
            temp.setModel(tempPhone.getProduct().getModel());
            temp.setPrice(tempPhone.getProduct().getPrice());
            temp.setScreenSize(tempPhone.getProduct().getScreenSize());
            featureSize=tempPhone.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);
            ph.add(temp);
        }

        return ph;
    }

    /**
     * This methods gets a Phone given a phone id from the Phone table.
     * @param id An integer representing the phone id.
     * @return PhoneHandler
     * @see PhoneHandler
     * HTTP GET Request
     * Example Request: url/getPhone/3
     */
    @GetMapping("/getPhone/{id}")
    public PhoneHandler getPhone(@PathVariable int id){


        int featureSize;
        Phone tempPhone;
        PhoneHandler temp;

            temp = new PhoneHandler();

            tempPhone = phservice.getPhone(id);
            temp.setPhoneID(tempPhone.getPhoneID());
            temp.setBrandName(tempPhone.getProduct().getBrand().getName());
            temp.setModel(tempPhone.getProduct().getModel());
            temp.setPrice(tempPhone.getProduct().getPrice());
            temp.setScreenSize(tempPhone.getProduct().getScreenSize());
            featureSize=tempPhone.getProduct().getProductFeatures().size();
            List<String> tempFeatureList = new ArrayList<>(Collections.emptyList());
            for(int j=0;j<featureSize;j++){
                tempFeatureList.add(tempPhone.getProduct().getProductFeatures().get(j).getFeature().getFeature_name());

            }
            temp.setFeatureList(tempFeatureList);



        return temp;
    }

    /**
     * This methods deletes a Phone given a phone id.
     * @param id An integer representing the phone id.
     * HTTP DELETE Request
     * Example Request: url/deletePhone/3
     */
    @DeleteMapping("/deletePhone/{id}")
    public void deletePhone(@PathVariable int id){

        phservice.deletePhone(id);

    }
}
