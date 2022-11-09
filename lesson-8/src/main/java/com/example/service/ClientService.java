package com.example.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Page<ClientRepr> findAll(Integer page, Integer size);
    List<ClientRepr> findAll();
    Optional<ClientRepr> findById(Long id);
    void save(ClientRepr client);
    void delete(Long id);
}

