package com.example.service;

import java.math.BigDecimal;
import java.util.Objects;

public class LineItemRepr {
    private Long id;

    private ClientRepr client;

    private ProductRepr product;

    private BigDecimal price;

    private Integer qty;

    private String color;

    public LineItemRepr() {
    }

    public LineItemRepr(Long id, ClientRepr client, ProductRepr product, BigDecimal price, Integer qty, String color) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public LineItemRepr(Long id, Long productId, Long clientId) {
        this.id = id;
        this.client = new ClientRepr();
        this.client.setId(clientId);
        this.product = new ProductRepr();
        this.product.setId(productId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineItemRepr lineItem)) return false;
        return client.getId().equals(lineItem.getClient().getId())
                && product.getId().equals(lineItem.getProduct().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient().getId(), getProduct().getId());
    }
}