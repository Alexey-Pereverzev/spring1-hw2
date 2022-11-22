package com.example.service;

import com.example.persist.Client;
import com.example.persist.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClientRepr {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @NotEmpty
    @JsonIgnore
    private String matchingPassword;

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

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ClientRepr() {
    }

    public ClientRepr(String name
            , String password, String matchingPassword, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.roles = roles;
    }

    public ClientRepr(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.password = client.getPassword();
        this.roles = new HashSet<>(client.getRoles());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRepr that = (ClientRepr) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
