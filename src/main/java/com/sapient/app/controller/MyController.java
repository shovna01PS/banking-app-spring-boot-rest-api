package com.sapient.app.controller;

import com.sapient.app.dto.AddTransactionDto;
import com.sapient.app.model.Transactions;
import com.sapient.app.service.BankServiceImpl;
import com.sapient.app.repository.CustomerRepository;
import com.sapient.app.model.Customer;
import com.sapient.app.service.TransactionService;
import com.sapient.app.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/customer")
public class MyController {

    @Autowired
    BankServiceImpl bankService;
    @Autowired
    CustomerRepository repository;

    @Autowired
    TransactionServiceImpl TransactionService;

    @GetMapping("/getCustomerData/{id}")
    public ResponseEntity<Customer> getCustomerData(@PathVariable(value = "id") int id) {
//
        try{
            Customer customer = bankService.getCustomerById(id);

            if(customer!=null){
                return new ResponseEntity<>(customer,HttpStatus.OK);
            } else{
                System.out.println("Customer not found for ID : " + id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCustomerData/{email}")
    public ResponseEntity<Customer> getCustomerData(@PathVariable(value = "email") String email) {
//
        try{
            Customer customer = bankService.getCustomerByEmail(email);

            if(customer!=null){
                return new ResponseEntity<>(customer,HttpStatus.OK);
            } else{
                System.out.println("Customer not found for Email : " + email);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//        return bankService.createCustomer(dto);
        try{
            bankService.createCustomer(customer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deposit/{amount}")
    public ResponseEntity<Customer> deposit(@PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
//        return bankService.deposit(amount, customer);
        try{
            bankService.deposit(amount, customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/withdraw/{amount}")
    public ResponseEntity<Customer> withdraw(@PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
//        return bankService.withdraw(amount, customer);
        try{
            bankService.withdraw(amount, customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applyloan/loantype={loanType}years={years}amount={amount}")
    public ResponseEntity<Customer> applyLoan(@PathVariable(value = "loanType") int loanType, @PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
        return bankService.applyLoan(loanType, years, amount, customer);

    }

//    @GetMapping("/open_fixed_deposit/years={years}amount={amount}")
//    public ResponseEntity<Customer> openFD(@PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
//        return bankService.openFD(years, amount, customer);
//    }

    @PostMapping("/addTransaction")
    public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions t ){
        try{
//            Transactions transaction = bankService.addTransaction(dto);
//            return new ResponseEntity<>(transaction,HttpStatus.OK);
              TransactionService.addTransactions(t);
            //Transactions transaction = Transactions;
            return new ResponseEntity<>(t,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTransaction")
    public ResponseEntity<List<Transactions>> getTransaction(@PathVariable(value = "id") int id){
        try{
            List<Transactions> t = TransactionService.getAllTransactionsByID(id);
            return new ResponseEntity<>(t, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
