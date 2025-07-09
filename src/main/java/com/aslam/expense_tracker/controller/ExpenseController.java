package com.aslam.expense_tracker.controller;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    //  Add New Expense
    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> addExpense(@RequestBody @Valid ExpenseRequestDTO dto) {
        ExpenseResponseDTO savedExpense = expenseService.addExpense(dto);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    //  Get All Expenses
    @GetMapping
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAll();
    }

    //  Filter by Category
    @GetMapping("/category/{category}")
    public List<ExpenseResponseDTO> getByCategory(@PathVariable String category) {
        return expenseService.getByCategory(category);
    }

    //  Filter by Date
    @GetMapping("/date")
    public List<ExpenseResponseDTO> getByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return expenseService.getByDate(date);
    }

    //  Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  Monthly Summary
    @GetMapping("/summary")
    public double getMonthlySummary(@RequestParam int year, @RequestParam int month) {
        return expenseService.getMonthlyTotal(year, month);
    }
}
