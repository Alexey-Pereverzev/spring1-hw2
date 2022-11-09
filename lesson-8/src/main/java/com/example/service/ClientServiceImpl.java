package com.example.service;

import com.example.persist.Client;
import com.example.persist.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<ClientRepr> findAll(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size)).map(ClientRepr::new);
    }

    @Override
    public List<ClientRepr> findAll() {
        return repository.findAll().stream().map(ClientRepr::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<ClientRepr> findById(Long id) {
        return repository.findById(id).map(ClientRepr::new);
    }

    @Transactional
    @Override
    public void save(ClientRepr client) {
        Client clientToSave = new Client(client);
        clientToSave.setPassword(passwordEncoder.encode(clientToSave.getPassword()));
        repository.save(clientToSave);
        if (client.getId() == null) {
            client.setId(clientToSave.getId());
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
