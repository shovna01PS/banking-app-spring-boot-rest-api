package com.sapient.app.service;

import com.sapient.app.dto.AddTransactionDto;
//import com.sapient.app.model.Transactions;
import com.sapient.app.repository.CustomerRepository;
import com.sapient.app.dao.BankDaoImpl;
import com.sapient.app.dao.BankDao;
import com.sapient.app.model.Customer;
//import com.sapient.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService{
    @Autowired
    BankDao bankDao;
    @Autowired
    CustomerRepository repository;

//    @Autowired
//    TransactionRepository tranRepo;

//    public BankServiceImpl(Repository repository) {
//        this.repository = repository;
//        bankDao = new BankDaoImpl(this.repository);
//    }

    @Override
    public Customer getCustomerById(int id) {
        return bankDao.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return bankDao.getCustomerByEmail(email);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        System.out.println("New Customer Service: "+customer);
        return bankDao.createCustomer(customer);
    }

    @Override
    public Customer deposit(double amount, Customer customer) {
        return bankDao.deposit(amount, customer);
    }

    @Override
    public Customer withdraw(double amount, Customer customer) {
        return bankDao.withdraw(amount, customer);
    }

    @Override
    public ResponseEntity<Customer> applyLoan(int loanType, int years, double amount, Customer customer) {
        return bankDao.applyLoan(loanType, years, amount, customer);
    }

//    @Override
//    public ResponseEntity<Customer> openFD(int years, double amount, Customer customer){
//
//    }

        // later on delete

//    @Override
//    public  Transactions addTransaction(AddTransactionDto dto){
//
//        Customer c = repository.findById(dto.getCusid()).get();
//
//        Transactions t = new Transactions(dto.getId(), c, dto.getType(), dto.getAmt());
//
//        return tranRepo.save(t);
//
//    }
}
