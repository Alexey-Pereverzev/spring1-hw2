package com.example.persist;

import com.example.service.ClientRepr;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String name;

    @Column(nullable = false, length = 512)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clients_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(ClientRepr client) {
        this.id = client.getId();
        this.name = client.getName();
        this.password = client.getPassword();
        this.roles = client.getRoles();
    }

    public Client(String name, String password , Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}
