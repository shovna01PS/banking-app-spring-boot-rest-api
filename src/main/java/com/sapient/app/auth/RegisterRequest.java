package com.sapient.app.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "UserPassword cannot be empty")
    //@Size(min=6,max=15,message = "Password length should be between 6 to 15")
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+]).*$", message = "Password must be alphanumeric and contain at least one special character")
    private String password;

    @NotEmpty(message = "CustomerName cannot be empty")
    private String name;

    @NotEmpty(message = "Gender cannot be empty")
    private String gender;

    //@Pattern(regexp = "^\\d{10}$")
   // @NotEmpty(message = "Mobile cannot be empty")
    private String phone;

    //@Pattern(regexp = "^\\d{12}$")
    //@NotEmpty(message = "Aadhaar cannot be empty")
    private String aadhaar;

    @Min(value = 2000,message = "Minimum balance should be 2000")
    @NotNull(message = "Balance cannot be null")
    private double balance;
}
