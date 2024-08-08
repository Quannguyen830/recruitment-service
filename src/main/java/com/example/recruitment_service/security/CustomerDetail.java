package com.example.recruitment_service.security;

import com.example.recruitment_service.model.user.Customer;
import com.example.recruitment_service.model.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomerDetail implements UserDetails {
    private final Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customer.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return customer.getUsername();
    }

    @Override
    public String getUsername() {
        return customer.getPassword();
    }
}
