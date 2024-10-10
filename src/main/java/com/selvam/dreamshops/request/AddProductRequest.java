package com.selvam.dreamshops.request;

import com.selvam.dreamshops.model.Category;
import lombok.Data;

import java.math.BigInteger;
@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigInteger price;
    private int inventory;
    private String description;
    private Category category;
}
