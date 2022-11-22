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
    private Client client;

    @ManyToOne
    private Product product;

    private BigDecimal price;

    private Integer qty;

    private String color;

    public LineItem() {
    }

    public LineItem(Client client, Product product, BigDecimal price, Integer qty, String color) {
        this.client = client;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public LineItem(LineItemRepr lineItem) {
        this.client = new Client(lineItem.getClient());
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
