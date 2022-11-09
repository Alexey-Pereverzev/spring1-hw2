package com.example.service;

import com.example.persist.Customer;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerRepr {

    private Long id;

    @NotEmpty
    private String fullName;

    private List<LineItemRepr> lineItems;

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

    public CustomerRepr() {
    }

    public List<LineItemRepr> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemRepr> lineItems) {
        this.lineItems = lineItems;
    }


    public CustomerRepr(Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.lineItems = customer.getLineItems().stream().map(LineItemRepr::new).collect(Collectors.toList());
    }




}
