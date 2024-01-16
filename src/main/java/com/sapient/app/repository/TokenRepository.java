package com.sapient.app.repository;


import com.sapient.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = "select t from Token t inner join Customer c on t.customer.id = c.id where c.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);

    @Query(value = "select t from Token t where t.customer.id = :id")
    List<Token> getAllTokenByUserId(@Param("id") int id);
}
