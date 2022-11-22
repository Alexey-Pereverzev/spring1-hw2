package com.example.controller;

import com.example.service.CartService;
import com.example.service.LineItemRepr;
import com.example.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final CartService cartService;

    private final ProductService productService;


    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model) {
        logger.info("List page requested");
        model.addAttribute("cart", cartService.showCartForCurrentClient());
        return "cart_form";
    }

    @Secured({"ROLE_GUEST"})
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        logger.info("Add to cart product for id {} requested", id);
        cartService.addToCart(productService.findById(id).orElseThrow(NotFoundException::new));
        return "redirect:/cart";
    }

    @Secured({"ROLE_GUEST"})
    @DeleteMapping("/delete/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Line item delete request");
        LineItemRepr lineItem = cartService.showCartForCurrentClient().stream().filter(li -> Objects.equals(li.getId(), id))
                .findFirst().orElseThrow();
        cartService.deleteLineItem(lineItem);
        return "redirect:/cart";
    }


}
