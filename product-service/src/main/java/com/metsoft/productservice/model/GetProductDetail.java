package com.metsoft.productservice.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDetail {

    private int id;


    private String name;


    private String description;

    private double price;
    private Category category;

}
