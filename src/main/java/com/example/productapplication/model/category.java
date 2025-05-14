package com.example.productapplication.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@Getter
@Setter
@Entity
public class category extends Basemodel{
    private String title;

}
