package com.example.productapplication.controller;

import com.example.productapplication.dto.createproductrequestdto;
import com.example.productapplication.model.product;
import com.example.productapplication.service.FakeStoreProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productcontroller {
    private FakeStoreProductService service;

    public productcontroller(FakeStoreProductService inputService){
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    public  ResponseEntity<product> getproductById(@PathVariable("id") Integer id){
        if(id == null){
            throw new IllegalArgumentException("id can not be null");
        }
        product product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    //----------------------------------------------------------------------------------------------

    @GetMapping("/products")
    public ResponseEntity<List<product>> getAllproducts(){
        List<product> products = service.getAllproducts();
        return ResponseEntity.ok(products);
    }

    //-----------------------------------------------------------------------------------------------

    @PostMapping("/products")
    public ResponseEntity<product> createproduct(@RequestBody createproductrequestdto request){

        return ResponseEntity.ok(service.createProduct(request.getTitle(),
                request.getImageURL(), request.getCategory().getTitle(),request.getDescription()));
    }

    //-------------------------------------------------------------------------------------------------

    @PutMapping("/products/{id}")
    public void updateproduct(){

    }

    //---------------------------------------------------------------------------------------------------

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Integer id){
        service.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully! :)");

    }

}
