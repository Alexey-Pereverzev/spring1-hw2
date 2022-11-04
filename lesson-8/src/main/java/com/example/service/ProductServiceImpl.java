package com.example.service;

import com.example.persist.Product;
import com.example.persist.ProductMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMatrix matrix;

    @Autowired
    public ProductServiceImpl(ProductMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public Page<ProductRepr> findAll(Integer page, Integer size) {
        return matrix.findAll(PageRequest.of(page,size)).map(ProductRepr::new);
    }

    @Override
    public List<ProductRepr> findAll() {
        return matrix.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ProductRepr> findById(Long id) {
        return matrix.findById(id).map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr product) {
        matrix.save(new Product(product));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        matrix.deleteById(id);
    }

}

