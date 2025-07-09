package com.aslam.expense_tracker.repository;

import com.aslam.expense_tracker.model.Expense;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    @Test
    @DisplayName("Should save and retrieve expense correctly")
    void shouldSaveAndFindExpense() {
        Expense expense = new Expense();
        expense.setAmount(250.0);
        expense.setCategory("Transport");
        expense.setDate(LocalDate.of(2025, 7, 8));
        expense.setDescription("Cab ride");

        Expense saved = repository.save(expense);

        assertNotNull(saved.getId());
        assertEquals("Transport", saved.getCategory());
        assertEquals(250.0, saved.getAmount());
    }

    @Test
    @DisplayName("Should fetch all stored expenses")
    void shouldFindAllExpenses() {
        Expense one = new Expense();
        one.setAmount(100);
        one.setCategory("Food");
        one.setDate(LocalDate.now());
        one.setDescription("Lunch");

        Expense two = new Expense();
        two.setAmount(300);
        two.setCategory("Utilities");
        two.setDate(LocalDate.now());
        two.setDescription("Electricity");

        repository.saveAll(List.of(one, two));

        List<Expense> allExpenses = repository.findAll();
        assertEquals(2, allExpenses.size());
    }
}