package com.example.productapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createproductrequestdto {
    private String title;
    private String imageURL;
    private String description;
    private categoryrequestdto category;

}
