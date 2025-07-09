package com.aslam.expense_tracker.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0.01")
    private Double amount;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Size(max = 100, message = "Description can't exceed 100 characters")
    private String description;

}
