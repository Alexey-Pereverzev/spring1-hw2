package com.example.rest;

import com.example.controller.BadRequestException;
import com.example.controller.NotFoundException;
import com.example.service.ProductRepr;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")         // версия v1
public class ProductResource {
    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ProductRepr> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ProductRepr findById(@PathVariable("id") Long id) {
        return productService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = "application/json")
    public ProductRepr create(@RequestBody ProductRepr productRepr) {
        if (productRepr.getId() != null) {
            throw new BadRequestException();
        }
        productService.save(productRepr);
        return productRepr;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductRepr productRepr) {
        if (productRepr.getId() == null) {
            throw new BadRequestException();
        }
        productService.save(productRepr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }
}
