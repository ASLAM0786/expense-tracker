package com.aslam.expense_tracker.repository;

import com.aslam.expense_tracker.model.Expense;
import com.aslam.expense_tracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Use in-memory DB
class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = createTestUser();
    }

    private User createTestUser() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setUsername("john786");
        user.setPassword("password");
        user.setRole("USER");
        return entityManager.persist(user);
    }

    @Test
    @DisplayName("Should save and retrieve a single expense correctly")
    void shouldSaveAndFindExpense() {
        // Arrange
        Expense expense = new Expense();
        expense.setAmount(250.0);
        expense.setCategory("Transport");
        expense.setDate(LocalDate.of(2025, 7, 8));
        expense.setDescription("Cab ride");
        expense.setUser(testUser);

        // Act
        repository.save(expense);
        List<Expense> found = repository.findAll();

        // Assert
        assertThat(found).hasSize(1);
        assertThat(found.getFirst().getUser().getEmail()).isEqualTo("john@example.com");
    }

    @Test
    @DisplayName("Should retrieve all expenses for a user")
    void shouldFindAllExpenses() {
        // Arrange
        Expense one = new Expense();
        one.setAmount(100);
        one.setCategory("Food");
        one.setDate(LocalDate.now());
        one.setDescription("Lunch");
        one.setUser(testUser);

        Expense two = new Expense();
        two.setAmount(300);
        two.setCategory("Utilities");
        two.setDate(LocalDate.now());
        two.setDescription("Electricity");
        two.setUser(testUser);

        repository.saveAll(List.of(one, two));

        // Act
        List<Expense> allExpenses = repository.findAll();

        // Assert
        assertEquals(2, allExpenses.size());
    }
}