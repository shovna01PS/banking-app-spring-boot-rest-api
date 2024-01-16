package com.sapient.app.dao;

//import com.sapient.app.model.Transactions;
import com.sapient.app.model.Transactions;
import com.sapient.app.repository.CustomerRepository;
import com.sapient.app.model.Customer;
import com.sapient.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@org.springframework.stereotype.Repository
public class BankDaoImpl implements BankDao {

    @Autowired
            CustomerRepository customerRepo;

    @Autowired
    TransactionRepository tRepo;

    Map<Integer, Double> idToBalance = new HashMap<>();
    LogManager logManager = LogManager.getLogManager();
    Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //int counter = 0;
    double minBalance = 500.0d;
    //Repository repository;
    List<Customer> customers = new ArrayList<>();


//    public BankDaoImpl(Repository repository) {
//        this.repository = repository;
//        Customer customer = new Customer(1, "name", "mail", "gender", 0, 0, 0);
//        customers.add(customer);
//        idToBalance.put(customer.getId(), customer.getBalance());
//    }

    @Override
    public Customer getCustomerById(int id){
            Optional<Customer> cs = customerRepo.findById(id);
            if (cs.isPresent()) {
                logger.log(Level.INFO, "Customer Found");
                return cs.get();
            }
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String email){
        Optional<Customer> cs = customerRepo.findByEmail(email);
        if (cs.isPresent()) {
            logger.log(Level.INFO, "Customer Found");
            return cs.get();
        }
        return null;
    }

    @Override
    public Customer createCustomer(Customer customer) {
            //Thread.currentThread().setPriority(8);
            customerRepo.save(customer);
            return customer;
    }

    @Override
    public Customer deposit(double amount, Customer customer) {
        Customer localCus = customerRepo.findById(customer.getId()).get();
        double finalAmount = customer.getBalance() + amount;
        localCus.setBalance(finalAmount);
        customerRepo.save(localCus);
        tRepo.save(Transactions.builder()
                        .cus(localCus)
                        .Transaction_type("Credit")
                        .amount(amount)
                        .build());
        return localCus;
    }

    @Override
    public Customer withdraw(double amount, Customer customer) {
        Customer localCus = customerRepo.findById(customer.getId()).get();
        if((localCus.getBalance() - amount) > minBalance) {
            double finalAmount = localCus.getBalance() - amount;
            localCus.setBalance(finalAmount);
            customerRepo.save(localCus);
            tRepo.save(Transactions.builder()
                    .cus(localCus)
                    .Transaction_type("Debit")
                    .amount(amount)
                    .build());
            return localCus;
        } else {logger.log(Level.INFO, "Not eligible for withdraw owing to insufficient balance ");}
        return null;
    }

    @Override
    public ResponseEntity<Customer> applyLoan(@PathVariable(value = "loanType") int loanType, @PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
        int ROI = switch (loanType) {
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 4;
            case 4 -> 5;
            default -> 6;
        };

        double interestPaid, principalPaid, newBalance;
        double monthlyInterestRate, monthlyPayment;
        int month;
        int numMonths = years * 12;

        monthlyInterestRate = ROI / 1200.0d;
        monthlyPayment = (float) (amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12)));
        System.out.println("Monthly Payment: " + monthlyPayment);
        System.out.println("Total Payment: " + monthlyPayment * years * 12);


        for (month = 1; month <= numMonths; month++) {
            interestPaid = amount * (monthlyInterestRate / 100);
            principalPaid = monthlyPayment - interestPaid;
            newBalance = amount - principalPaid;
            amount = newBalance;
        }
        customer.setBalance(customer.getBalance() + amount);

        idToBalance.put(customer.getId(), customer.getBalance());

        try {
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Customer> openFD(@PathVariable(value = "years") int years, @PathVariable(value = "amount") double amount, @RequestBody Customer customer) {
        try {
            int ROI = 3;
            amount += (amount * ROI * years / 100);
            customer.setBalance(customer.getBalance() + amount);
            logger.log(Level.INFO, "Customer total profit: " + amount);
            logger.log(Level.INFO, "Customer Balance in account: " + customer.getBalance());

            idToBalance.put(customer.getId(), customer.getBalance());
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}