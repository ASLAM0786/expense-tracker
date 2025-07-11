package com.aslam.expense_tracker.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseRequestDTOValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(10.0);
        dto.setCategory("Books");
        dto.setDate(LocalDate.now());
        dto.setDescription("Bought books");
        dto.setUserId(1L);  // New field included

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void whenFieldsInvalid_thenExpectViolations() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(0.0);     // Invalid: below minimum
        dto.setCategory("");    // Invalid: blank
        dto.setDate(null);      // Invalid: null
        dto.setUserId(null);    // Invalid: null

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);
        assertEquals(4, violations.size(), "Expected 4 violations");

        for (ConstraintViolation<ExpenseRequestDTO> violation : violations) {
            System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
        }
    }
}