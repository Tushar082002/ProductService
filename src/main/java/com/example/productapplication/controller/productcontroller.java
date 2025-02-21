package com.example.productapplication.controller;

import com.example.productapplication.model.product;
import com.example.productapplication.service.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class productcontroller {
    private FakeStoreProductService service;

    public productcontroller(FakeStoreProductService inputService){
        this.service = inputService;
    }

    @GetMapping("/products/{id}")
    public product getproductById(@PathVariable("id") Integer id){
        if(id == null){
            throw new IllegalArgumentException("id can not be null");
        }
        return service.getProductById(id);
    }

    @GetMapping("/products")
    public void getAllproducts(){

    }

    @PostMapping("/products")
    public void createproduct(){

    }

    @PutMapping("/products/{id}")
    public void updateproduct(){

    }

    @DeleteMapping("/products")
    public void deleteproduct(){

    }

}
