package com.example.recruitment_service.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Log4j2
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**", "/v3/api-docs",
                                "/swagger-ui/**").permitAll()
                )
                .oauth2ResourceServer(configurer -> {
                    configurer.authenticationEntryPoint(customAuthenticationEntryPoint);
                    configurer.jwt(jwtConfigurer -> {
                        try {
                            jwtConfigurer.decoder(NimbusJwtDecoder
                                    .withPublicKey(readPublicKey(new ClassPathResource("public.pem"))).build());
                        } catch (IOException e) {
                            log.error(e);
                            throw new RuntimeException(e);
                        }
                    });
                });

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        try {
            return new NimbusJwtEncoder(new ImmutableJWKSet<>(
                    new JWKSet(new RSAKey.Builder(readPublicKey(new ClassPathResource("public.pem")))
                            .privateKey(readPrivateKey(new ClassPathResource("private.pem"))).build())));
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    private static RSAPublicKey readPublicKey(ClassPathResource classPathResource) throws IOException {
        return RsaKeyConverters.x509().convert(classPathResource.getInputStream());
    }

    private static RSAPrivateKey readPrivateKey(ClassPathResource classPathResource) throws IOException {
        return RsaKeyConverters.pkcs8().convert(classPathResource.getInputStream());
    }
}
