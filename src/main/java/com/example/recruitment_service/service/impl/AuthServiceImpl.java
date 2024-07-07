package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.dto.request.entity.LoginDtoIn;
import com.example.recruitment_service.dto.response.LoginDtoOut;
import com.example.recruitment_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService userDetailsService;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginDtoOut login(LoginDtoIn loginDtoIn) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(loginDtoIn.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, "Invalid username or password");
        }
        if(!passwordEncoder.matches(loginDtoIn.getPassword(), userDetails.getPassword())) {
            throw new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, "Invalid password");
        }

        return LoginDtoOut.builder().token(grantAccess(userDetails.getUsername())).build();
    }

    private String grantAccess(String username) {
        long iat = System.currentTimeMillis() / 1000;
        long exp = iat + Duration.ofHours(8).toSeconds();

        JwtEncoderParameters parameters = JwtEncoderParameters.from(JwsHeader.with(SignatureAlgorithm.RS256).build(),
                JwtClaimsSet.builder().subject(username).issuedAt(Instant.ofEpochSecond(iat))
                        .expiresAt(Instant.ofEpochSecond(exp)).claim("user_name", username)
                        .claim("scope", List.of("ADMIN")).build());
        try {
            return jwtEncoder.encode(parameters).getTokenValue();
        } catch (JwtEncodingException e) {
            log.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }
}
