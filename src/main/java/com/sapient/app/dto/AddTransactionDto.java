package com.sapient.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTransactionDto {
    private int id;
    private String type;
    private float amt;
    private int cusid;
}
