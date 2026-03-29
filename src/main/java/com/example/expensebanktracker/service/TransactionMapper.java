package com.example.expensebanktracker.service;


import com.example.expensebanktracker.entity.Category;
import com.example.expensebanktracker.dto.TransactionDTO;
import com.example.expensebanktracker.entity.Transaction;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction t) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(t.getId());
        dto.setAmount(t.getAmount());
        dto.setDescription(t.getDescription());
        dto.setDate(t.getDate());

        if (t.getCategory() != null) {
            dto.setCategoryId(t.getCategory().getId());
        }

        return dto;
    }

    public static Transaction toEntity(TransactionDTO dto, Category category) {
        Transaction t = new Transaction();
        t.setAmount(dto.getAmount());
        t.setDescription(dto.getDescription());
        t.setDate(dto.getDate());
        t.setCategory(category);
        return t;
    }
}
