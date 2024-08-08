package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;
import com.example.recruitment_service.model.user.Customer;
import com.example.recruitment_service.model.user.Role;
import com.example.recruitment_service.repository.CustomerRepository;
import com.example.recruitment_service.security.CustomerDetail;
import com.example.recruitment_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    @Override
    public Customer register(LoginDtoIn loginDtoIn) {
        Customer customer = new Customer();
        if(customerRepository.findByUsername(loginDtoIn.getUsername()).isEmpty()) {
            customer.setUsername(loginDtoIn.getUsername());
            customer.setPassword(passwordEncoder.encode(loginDtoIn.getPassword()));
            customer.setRole(new ArrayList<>());
            customerRepository.save(customer);
            System.out.println("Not found");
        } else {
            customer = customerRepository.findByUsername(loginDtoIn.getUsername()).get();
            System.out.println("Found");
        }
        return customer;
    }

    @Override
    public LoginDtoOut login(LoginDtoIn loginDtoIn) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(loginDtoIn.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, "User not found");
        }

        if(!passwordEncoder.matches(loginDtoIn.getPassword(), userDetails.getPassword())) {
            throw new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, "Password not found");
        }

        return LoginDtoOut.builder().token(generateAccessToken(userDetails.getUsername())).build();
    }

    private String generateAccessToken(String username) {
        long iat = System.currentTimeMillis() / 1000;
        long expireAt = iat + Duration.ofHours(8).toSeconds();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(SignatureAlgorithm.RS256).build(),
                JwtClaimsSet.builder()
                        .subject(username)
                        .claim("username", username)
                        .claim("scope", List.of("ADMIN"))
                        .issuedAt(Instant.ofEpochSecond(iat))
                        .expiresAt(Instant.ofEpochSecond(expireAt))
                        .build()
        );

        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
