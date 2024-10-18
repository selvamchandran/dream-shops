package com.selvam.dreamshops.dto;


import com.selvam.dreamshops.model.Cart;
import com.selvam.dreamshops.model.Order;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
