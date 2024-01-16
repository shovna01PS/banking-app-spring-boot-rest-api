package com.sapient.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerDto {

    private int id, aadhaar, phone;
    private String name, email, gender;
    private float balance;
}
