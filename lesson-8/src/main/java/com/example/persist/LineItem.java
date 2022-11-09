package com.example.persist;

import com.example.service.LineItemRepr;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "line_items")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private BigDecimal price;

    private Integer qty;

    private String color;

    public LineItem() {
    }

    public LineItem(Customer customer, Product product, BigDecimal price, Integer qty, String color) {
        this.customer = customer;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public LineItem(LineItemRepr lineItem) {
        this.customer = new Customer(lineItem.getCustomer());
        this.product = new Product(lineItem.getProduct());
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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
