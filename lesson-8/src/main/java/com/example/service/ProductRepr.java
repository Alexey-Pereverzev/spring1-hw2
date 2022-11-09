package com.example.service;

import com.example.persist.LineItem;
import com.example.persist.Product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

public class ProductRepr {

    private Long id;

    @NotEmpty
    private String title;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal cost;

    private List<LineItem> lineItems;

    public ProductRepr(String title, BigDecimal cost, List<LineItem> lineItems) {
        this.cost = cost;
        this.title = title;
        this.lineItems = lineItems;
    }

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.cost = product.getCost();
        this.title = product.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        BigDecimal zero = new BigDecimal(0);
        if (cost.compareTo(zero) < 0) {
            cost = zero;
        }
        this.cost = cost;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
