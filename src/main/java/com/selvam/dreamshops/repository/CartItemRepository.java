package com.selvam.dreamshops.repository;

import com.selvam.dreamshops.model.Cart;
import com.selvam.dreamshops.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCartId(Long id);
}
