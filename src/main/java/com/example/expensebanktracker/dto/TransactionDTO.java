package com.example.expensebanktracker.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionDTO {
    private Long id;
    private Double amount;
    private String description;
    private LocalDate date;
    private Long categoryId;
}
