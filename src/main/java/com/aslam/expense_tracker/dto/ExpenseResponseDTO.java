package com.aslam.expense_tracker.dto;

import lombok.Data;

@Data
public class ExpenseResponseDTO {
    private long id;
    private double amount;
    private String category;
    private String description;
}
