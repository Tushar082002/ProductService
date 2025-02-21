package com.example.productapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product {
    private Integer id;
    private String title;
    private String description;
    private category category;
    private String image;
}
