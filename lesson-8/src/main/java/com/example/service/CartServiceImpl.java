package com.example.service;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartServiceImpl implements CartService {

    private final ArrayList<LineItemRepr> cart = new ArrayList<>();

    @Override
    public void addToCart(ProductRepr product) {
        boolean isInCart = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId()==product.getId()) {
                cart.get(i).setQty(cart.get(i).getQty()+1);
                isInCart = true;
                break;
            }
        }
        if (!isInCart) {
            cart.add(new LineItemRepr(new CustomerRepr(), product, product.getCost(), 1,""));
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
