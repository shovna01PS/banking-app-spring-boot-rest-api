package com.sapient.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sapient.app.model.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>{
    @Query("select t from Transactions t where t.cus.id = :id")
    List<Transactions> findCustomerById(@Param(value = "id") Integer id);
}