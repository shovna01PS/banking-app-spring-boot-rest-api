package com.sapient.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "Transaction_history")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(nullable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_ID")
    private Customer cus;

//    @ManyToOne
//    @JoinColumn(name="cus_id",referencedColumnName = "id",insertable = false,updatable = false)
//    private  Customer customer;

    @Column
    private String Transaction_type;

    @Column
    private double amount;

}