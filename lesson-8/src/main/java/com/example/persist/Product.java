package com.example.persist;

import com.example.service.ProductRepr;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @Column(length = 32, nullable = false)
    private BigDecimal cost;

    @OneToMany(mappedBy = "product")
    private List<LineItem> lineItems;

    public Product(String title, BigDecimal cost, List<LineItem> lineItems) {
        this.cost = cost;
        this.title = title;
        this.lineItems = lineItems;
    }

    public Product() {
    }

    public Product(ProductRepr product) {
        this.id = product.getId();
        this.cost = product.getCost();
        this.title = product.getTitle();
        this.lineItems = product.getLineItems();
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
            cost=zero;
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
