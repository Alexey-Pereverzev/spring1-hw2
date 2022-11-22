package com.example.service;

import com.example.persist.LineItem;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class LineItemRepr {
    private Long id;

    @NotEmpty
    private ClientRepr client;

    @NotEmpty
    private ProductRepr product;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    private Integer qty;

    private String color;


    public LineItemRepr() {
    }

    public LineItemRepr(ClientRepr client, ProductRepr product, BigDecimal price, Integer qty, String color) {
        this.client = client;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public LineItemRepr(LineItem lineItem) {
        this.client = new ClientRepr(lineItem.getClient());
        this.product = new ProductRepr(lineItem.getProduct());
        this.price = lineItem.getPrice();
        this.qty = lineItem.getQty();
        this.color = lineItem.getColor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientRepr getClient() {
        return client;
    }

    public void setClient(ClientRepr client) {
        this.client = client;
    }

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}