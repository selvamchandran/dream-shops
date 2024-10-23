package com.selvam.dreamshops.service.cart;

import com.selvam.dreamshops.model.Cart;
import com.selvam.dreamshops.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
