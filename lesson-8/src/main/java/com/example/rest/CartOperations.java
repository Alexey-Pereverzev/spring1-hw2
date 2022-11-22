package com.example.rest;

import com.example.controller.BadRequestException;
import com.example.service.CartService;
import com.example.service.LineItemRepr;
import com.example.service.ProductRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")         // версия v1
public class CartOperations {
    private final CartService cartService;

    @Autowired
    public CartOperations(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(consumes = "application/json")
    public ProductRepr addToCart(@RequestBody ProductRepr product) {
        cartService.addToCart(product);
        return product;
    }

    @GetMapping(path = "/show", produces = "application/json")
    public List<LineItemRepr> showCart() {
        return cartService.showCartForCurrentClient();
    }

    @DeleteMapping("/{id}")
    public void deleteLineItem(@PathVariable("id") Long id) {
        LineItemRepr lineItem = cartService.showCartForCurrentClient().get(Math.toIntExact(id));
        cartService.deleteLineItem(lineItem);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }

}
