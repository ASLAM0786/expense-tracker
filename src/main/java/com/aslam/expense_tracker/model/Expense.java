package com.aslam.expense_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double amount;
    private String category;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
