package com.example.productapplication.controller;

import com.example.productapplication.dto.createproductrequestdto;
import com.example.productapplication.exception.ProductNotFoundException;
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
    public  ResponseEntity<product> getproductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        if(id <= 0){
            throw new IllegalArgumentException("id is Invalid");
        }
        product product = service.getProductById(id);
        if(product == null){
            throw new ProductNotFoundException("Product not Found");
        }
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
        if (request.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if (request.getDescription() == null){
            throw new IllegalArgumentException("Description cannot be null");
        }
        if(request.getImageURL() == null){
            throw new IllegalArgumentException("ImageURL cannot be null");
        }

        return ResponseEntity.ok(service.createProduct(request.getTitle(),
                request.getImageURL(), request.getCategory().getTitle(),request.getDescription()));
    }

    //-------------------------------------------------------------------------------------------------

    @PutMapping("/products/{id}")
    public void updateproduct(){

    }

    //---------------------------------------------------------------------------------------------------

    @DeleteMapping("/products/{id}")
    public ResponseEntity<product> deleteProductById(@PathVariable("id") Integer id) throws  ProductNotFoundException{
        if(id <= 0){
            throw new IllegalArgumentException("id is Invalid");
        }

        product product = service.deleteProductById(id);
        if(product == null){
            throw new ProductNotFoundException("Product not Found");
        }
        return ResponseEntity.ok(product);

    }

}
