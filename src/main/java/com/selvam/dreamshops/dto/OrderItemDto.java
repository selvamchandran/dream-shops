package com.selvam.dreamshops.dto;

import com.selvam.dreamshops.model.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;


}
