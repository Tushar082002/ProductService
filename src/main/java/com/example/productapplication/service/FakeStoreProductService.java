package com.example.productapplication.service;
import com.example.productapplication.dto.FakeStoreResponsedto;
import com.example.productapplication.model.category;
import com.example.productapplication.model.product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.RequestEntity.delete;

@Service
public class FakeStoreProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public product getProductById(Integer id) {
        product product = new product();
        //1. call a request Api
        ResponseEntity<FakeStoreResponsedto> fakeStoreResponse =  restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponsedto.class);
        //2. get the response
        FakeStoreResponsedto response = fakeStoreResponse.getBody();
        if(response == null) {
            throw new IllegalArgumentException("FakeStoreProduct not found");
        }
        //3. convert the response to product model
        product = convertFakeStoreResponseToProduct(response);
        return product;
    }


    private product convertFakeStoreResponseToProduct(FakeStoreResponsedto response) {
        product product = new product();
        category category = new category();
        category.setTitle(response.getCategory());

        product.setCategory(category);
        product.setId(response.getId());
        product.setDescription(response.getDescription());
        product.setImage(response.getImage());
        product.setTitle(response.getTitle());
        return product;
    }

    //-----------------------------------------------------------------------------------------------------

    public List<product> getAllproducts(){
        //1. call a fakestore api
        List<product> products = new ArrayList<>();
        ResponseEntity<FakeStoreResponsedto[]> response =  restTemplate.getForEntity("https://fakestoreapi.com/products" , FakeStoreResponsedto[].class);

        //get a status code
        System.out.println("Status Code: " + response.getStatusCode());

        //2. converting into a list
        for(FakeStoreResponsedto FakeStoredto : response.getBody()){
            products.add(convertFakeStoreResponseToProduct(FakeStoredto));
        }

        return products;

    }

    //-----------------------------------------------------------------------------------------------------

    public  product createProduct(String title, String imageURL, String catTitle, String description) {
        product response = new product();
        FakeStoreResponsedto requestBody = new FakeStoreResponsedto();
        requestBody.setCategory(catTitle);
        requestBody.setDescription(description);
        requestBody.setTitle(title);
        requestBody.setImage(imageURL);

        ResponseEntity<FakeStoreResponsedto> fakestoreResponse =  restTemplate.postForEntity("https://fakestoreapi.com/products" , requestBody , FakeStoreResponsedto.class);
        System.out.println("Status Code: " + fakestoreResponse.getStatusCode());
        response = convertFakeStoreResponseToProduct(fakestoreResponse.getBody());


        return response;
    }


    public product deleteProductById(Integer id) {
        product producttodelete = getProductById(id);
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return producttodelete;

    }
}
