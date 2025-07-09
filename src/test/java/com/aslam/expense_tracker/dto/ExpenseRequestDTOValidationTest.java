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

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenFieldsInvalid_thenExpectViolations() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(0.0);
        dto.setCategory("");
        dto.setDate(null);

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);
        assertEquals(3, violations.size());
    }
}
