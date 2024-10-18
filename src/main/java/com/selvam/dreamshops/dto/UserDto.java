package com.selvam.dreamshops.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate OrderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDto> items;
}
