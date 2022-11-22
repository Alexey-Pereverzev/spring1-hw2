package com.example.service;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CartServiceImpl implements CartService {

    private final ArrayList<LineItemRepr> cart = new ArrayList<>();

    @Override
    public void addToCart(ProductRepr product) {
        boolean isInCart = false;
        for (LineItemRepr lineItemRepr : cart) {
            if (Objects.equals(lineItemRepr.getProduct().getId(), product.getId())) {
                lineItemRepr.setQty(lineItemRepr.getQty() + 1);
                isInCart = true;
                break;
            }
        }
        if (!isInCart) {
            cart.add(new LineItemRepr(new ClientRepr(), product, product.getCost(), 1, ""));
        }
    }

    @Override
    public List<LineItemRepr> showCart() {
        return cart;
    }

    @Override
    public void deleteLineItem(LineItemRepr lineItem) {
        cart.remove(lineItem);
    }

}
