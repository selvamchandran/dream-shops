package com.selvam.dreamshops.service.order;

import com.selvam.dreamshops.dto.OrderDto;
import com.selvam.dreamshops.model.Order;

import java.util.List;

public interface IOrderService {
    public Order placeOrder(Long userId);
    public OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
