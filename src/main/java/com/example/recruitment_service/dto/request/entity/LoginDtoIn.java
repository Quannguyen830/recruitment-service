package com.example.recruitment_service.dto.request.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDtoIn {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public String getUsername() {
        return this.username.toLowerCase();
    }
}
