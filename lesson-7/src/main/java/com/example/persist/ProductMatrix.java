package com.example.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMatrix extends JpaRepository<Product, Long> {


}
