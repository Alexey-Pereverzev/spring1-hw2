package com.example.security;

import com.example.persist.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ClientAuthService implements UserDetailsService {

    private final ClientRepository repository;

    @Autowired
    public ClientAuthService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return repository.findClientByName(name)
                .map(client -> new User(
                        client.getName(),
                        client.getPassword(),
                        client.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList()
                        ))).orElseThrow(() -> new UsernameNotFoundException("Client not found"));
    }
}
