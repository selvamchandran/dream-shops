package com.selvam.dreamshops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.selvam.dreamshops.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
}
