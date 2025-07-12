package com.aslam.expense_tracker.service;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    ExpenseResponseDTO addExpense(ExpenseRequestDTO dto,String username);

    List<ExpenseResponseDTO> getAll();

    List<ExpenseResponseDTO> getByCategory(String category);

    List<ExpenseResponseDTO> getByDate(LocalDate date);

    void delete(Long id);

    double getMonthlyTotal(int year, int month);
}
