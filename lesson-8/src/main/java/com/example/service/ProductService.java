package com.example.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductService {
    Page<ProductRepr> findAll(Integer page, Integer size);
    Optional<ProductRepr> findById(Long id);
    void save(ProductRepr user);
    void delete(Long id);
}
