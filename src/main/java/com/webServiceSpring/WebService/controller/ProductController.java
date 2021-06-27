package com.webServiceSpring.WebService.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webServiceSpring.WebService.entity.Brand;
import com.webServiceSpring.WebService.entity.Features;
import com.webServiceSpring.WebService.entity.JsonHandlers.ProductHandler;
import com.webServiceSpring.WebService.entity.Product;
import com.webServiceSpring.WebService.entity.connectors.ProductFeature;
import com.webServiceSpring.WebService.entity.connectors.ProductFeatureKeyHolder;
import com.webServiceSpring.WebService.service.BrandService;
import com.webServiceSpring.WebService.service.FeaturesService;
import com.webServiceSpring.WebService.service.ProductFeatureService;
import com.webServiceSpring.WebService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Mehmet Bengican Altunsu, Yusuf Gökberk Keptiğ
 * This class is the controller of Product Entity.
 * Any requests to modify or get Products goes through this controller.
 */

@RestController
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private BrandService bservice;
    @Autowired
    private FeaturesService fservice;
    @Autowired
    private ProductFeatureService pfservice;


    /**
     * This Method adds a Product to the Product Table.
     * @param productHandler
     * @see ProductHandler
     * @see Product
     * @return Product
     * HTTP POST Request
     * You can use this method by posting a JSON body that includes variables of the ProductHandler class.
     * Example Request: url/addProduct
     */

    @PostMapping("/addProduct")
    @JsonIgnoreProperties({"productList"})
    public Product addProduct(@RequestBody ProductHandler productHandler){
        Product p = new Product();
        p.setType(productHandler.getType());
        p.setModel(productHandler.getModel());
        p.setPrice(productHandler.getPrice());
        p.setScreenSize(productHandler.getScreenSize());
        Brand b = bservice.getBrand(productHandler.getBrandName());
        p.setBrand(b);
        b.addProduct(p);
        service.saveProduct(p);
        int size=productHandler.getFeatureList().size();

        ProductFeature pf = new ProductFeature();
        for(int i=0;i<size;i++){
            pf.setProduct(p);
            Features feature = fservice.getFeatureByName(productHandler.getFeatureList().get(i));
            pf.setFeature(feature);
            pf.setKey(new ProductFeatureKeyHolder(p.getPid(),feature.getFID()));

            pfservice.saveProductFeature(pf);
        }

        return p;
    }

    /**
     * This method gets all products from the Product table.
     * @return List <Product>
     * @see Product
     * HTTP GET Request
     * Example Request: url/getProducts
     *
     */
    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    /**
     * This method returns a product given a product id from the Product table.
     * @param id integer that represents product id.
     * @see Product
     * @return Product
     * HTTP GET Request
     * Example Request: url/getProduct/3
     */
    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProduct(id);
    }

    /**
     * This method deletes a product given a product id from the Product Table.
     * @param id an integer represents product id.
     * HTTP DELETE Request
     * Example Request: url/deleteProduct/3
     */
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }
}
