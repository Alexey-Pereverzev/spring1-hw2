package com.example.service;

import java.util.List;

public interface CartService {
    LineItemRepr addToCart(ProductRepr product);

    List<LineItemRepr> showCartForCurrentClient();

    void deleteLineItem(LineItemRepr lineItem);
}
