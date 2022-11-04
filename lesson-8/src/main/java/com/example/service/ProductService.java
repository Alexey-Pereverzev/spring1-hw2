package com.example.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<ProductRepr> findAll(Integer page, Integer size);
    List<ProductRepr> findAll();
    Optional<ProductRepr> findById(Long id);
    void save(ProductRepr user);
    void delete(Long id);


}
