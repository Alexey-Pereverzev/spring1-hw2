package com.example.persist;



import com.example.service.CustomerRepr;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "customer")
    private List<LineItem> lineItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Customer() {
    }

    public Customer(String fullName) {
        this.fullName = fullName;

        this.lineItems = new ArrayList<>();
    }

    public Customer(CustomerRepr customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.lineItems = customer.getLineItems().stream().map(LineItem::new).collect(Collectors.toList());
    }

}
