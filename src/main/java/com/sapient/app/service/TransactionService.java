package com.sapient.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sapient.app.model.Transactions;

@Service
public interface TransactionService {
    public List<Transactions> getAllTransactionsByID(Integer id);
    public void addTransactions(Transactions transaction);

}