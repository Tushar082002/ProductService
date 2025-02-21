package com.example.productapplication.service;
import com.example.productapplication.dto.FakeStoreResponsedto;
import com.example.productapplication.model.category;
import com.example.productapplication.model.product;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


}
