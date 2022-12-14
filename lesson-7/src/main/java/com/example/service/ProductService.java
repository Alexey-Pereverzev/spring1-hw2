package com.example.service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductRepr> findAll();
    Optional<ProductRepr> findById(Long id);
    void save(ProductRepr user);
    void delete(Long id);
}
