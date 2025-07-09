package com.aslam.expense_tracker.service;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.mapper.ExpenseMapper;
import com.aslam.expense_tracker.model.Expense;
import com.aslam.expense_tracker.repository.ExpenseRepository;
import com.aslam.expense_tracker.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository repository;

    @Mock
    private ExpenseMapper mapper;

    @InjectMocks
    private ExpenseServiceImpl service;

    @Test
    void shouldAddExpenseAndReturnDto() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(150.0);
        dto.setCategory("Utilities");
        dto.setDate(LocalDate.now());
        dto.setDescription("Electric Bill");

        Expense entity = new Expense();
        entity.setAmount(150.0);
        entity.setCategory("Utilities");
        entity.setDate(dto.getDate());
        entity.setDescription("Electric Bill");

        Expense savedEntity = new Expense();
        savedEntity.setId(1L);
        savedEntity.setAmount(150.0);
        savedEntity.setCategory("Utilities");
        savedEntity.setDate(dto.getDate());
        savedEntity.setDescription("Electric Bill");

        ExpenseResponseDTO response = new ExpenseResponseDTO();
        response.setId(1L);
        response.setAmount(150.0);
        response.setCategory("Utilities");
        response.setDescription("Electric Bill");

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(response);

        ExpenseResponseDTO result = service.addExpense(dto);

        assertEquals(1L, result.getId());
        assertEquals(150.0, result.getAmount());
        assertEquals("Utilities", result.getCategory());
    }
}
