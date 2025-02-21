package com.example.productapplication.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponsedto {
    private Integer id;
    private String title;
    private String description;
    private String image;
    private String category;
}
