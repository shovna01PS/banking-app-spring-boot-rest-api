package com.sapient.app.controller;

import com.sapient.app.model.Customer;
import com.sapient.app.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
class MyControllerTest {
    private final MyController myController;
    @Test
    void getCustomerData() {
            Customer customer = Customer.builder()
                    .id(1)
                    .name("Shovna")
                    .email("abc@gmail.com")
                    .gender("F")
                    .phone("1234567890")
                    .balance(0)
                    .password("Shovna@123")
                    .aadhaar("123456789012")
                    .role(Role.CUSTOMER)
                    .build();
            ResponseEntity<Customer> response;
            try {
                response = myController.createCustomer(customer);
            } catch (Exception e) {
                fail("Exception should not be thrown");
                return;
            }
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(customer.getName(), response.getBody().getName());
            assertEquals(customer.getGender(), response.getBody().getGender());
            assertEquals(customer.getAadhaar(), response.getBody().getAadhaar());
            assertEquals(customer.getPhone(), response.getBody().getPhone());
            assertEquals(customer.getEmail(), response.getBody().getEmail());
            assertEquals(customer.getPassword(), response.getBody().getPassword());
            assertEquals(customer.getBalance(), response.getBody().getBalance(), 0.01);
            assertEquals(customer.getRole(), response.getBody().getRole());
    }

    @Test
    void testGetCustomerData() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void applyLoan() {
    }

    @Test
    void addTransaction() {
    }

    @Test
    void getTransaction() {
    }
}