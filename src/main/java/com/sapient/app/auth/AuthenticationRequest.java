package com.sapient.app.auth;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Email
    @NotEmpty(message = "UserEmail cannot be empty")
    private String email;

    @NotEmpty(message = "UserPassword cannot be empty")
    @Size(min=6,max=15,message = "Password length should be between 6 to 15")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+]).*$", message = "Password must be alphanumeric and contain at least one special character")
    private String password;
}
