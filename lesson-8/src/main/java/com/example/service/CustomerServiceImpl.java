package com.example.service;

import com.example.persist.Customer;
import com.example.persist.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<CustomerRepr> findAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size)).map(CustomerRepr::new);
    }

    @Override
    public List<CustomerRepr> findAll() {
        return repository.findAll().stream().map(CustomerRepr::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<CustomerRepr> findById(Long id) {
        return repository.findById(id).map(CustomerRepr::new);
    }

    @Transactional
    @Override
    public void save(CustomerRepr customer) {
        repository.save(new Customer(customer));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
