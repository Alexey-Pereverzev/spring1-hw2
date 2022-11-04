package com.example.service;

import java.util.List;

public interface CartService {
    void addToCart(ProductRepr product);
    List<LineItemRepr> showCart();
    void deleteLineItem(LineItemRepr lineItem);
}
