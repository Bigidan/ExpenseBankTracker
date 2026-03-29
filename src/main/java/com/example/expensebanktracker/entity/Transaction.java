package com.example.expensebanktracker.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String description;

    private java.time.LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Category category;
}
