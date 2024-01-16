package com.sapient.app.dao;

import com.sapient.app.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface BankDao {

    public Customer getCustomerById(int id);

    public Customer getCustomerByEmail(String email);

    public Customer createCustomer(Customer customer);

    public Customer deposit(double amount, Customer customer);

    public Customer withdraw(double amount, Customer customer);

    public ResponseEntity<Customer> applyLoan(@PathVariable(value = "loanType") int loanType, @PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer);

    public ResponseEntity<Customer> openFD(@PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer);
}
