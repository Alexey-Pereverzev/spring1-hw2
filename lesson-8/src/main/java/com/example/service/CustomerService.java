package com.example.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Page<CustomerRepr> findAll(Integer page, Integer size);
    List<CustomerRepr> findAll();
    Optional<CustomerRepr> findById(Long id);
    void save(CustomerRepr customer);
    void delete(Long id);
}

