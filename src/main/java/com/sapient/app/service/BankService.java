package com.sapient.app.service;

import com.sapient.app.dto.AddTransactionDto;
import com.sapient.app.model.Customer;
//import com.sapient.app.model.Transactions;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface BankService {
    public Customer getCustomerById(int id);

    public Customer getCustomerByEmail(String email);

    public Customer createCustomer(Customer customer);

    public Customer deposit(double amount, Customer customer);

    public Customer withdraw(double amount, Customer customer);

    public ResponseEntity<Customer> applyLoan(@PathVariable(value = "loanType") int loanType, @PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer);

    //public ResponseEntity<Customer> openFD(@PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer);

//    public Transactions addTransaction(AddTransactionDto transactions);
}
