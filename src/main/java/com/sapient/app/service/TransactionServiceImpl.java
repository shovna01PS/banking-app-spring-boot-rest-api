package com.sapient.app.service;

import java.util.List;

import com.sapient.app.model.Transactions;
import com.sapient.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sapient.app.dao.BankDao;
//import com.sapient.app.repository.TransactionRepository;
//import com.sapient.app.model.Transactions;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository TRepo;

    @Autowired
    BankDao bankDao;

    @Override
    public void addTransactions(Transactions transaction) {
        TRepo.save(transaction);
    }

    @Override
    public List<Transactions> getAllTransactionsByID(Integer id) {
        return TRepo.findCustomerById(id);
    }
}