package com.aslam.expense_tracker.service;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.mapper.ExpenseMapper;
import com.aslam.expense_tracker.model.Expense;
import com.aslam.expense_tracker.model.User;
import com.aslam.expense_tracker.repository.ExpenseRepository;
import com.aslam.expense_tracker.repository.UserRepository;
import com.aslam.expense_tracker.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository repository;

    @Mock
    private ExpenseMapper mapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseServiceImpl service;

    @Test
    @DisplayName("Should save expense and return DTO correctly")
    void shouldAddExpenseAndReturnDto() {
        // Given
        String username = "aslam123";

        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(150.0);
        dto.setCategory("Utilities");
        dto.setDate(LocalDate.now());
        dto.setDescription("Electric Bill");

        Expense entity = new Expense();
        entity.setAmount(dto.getAmount());
        entity.setCategory(dto.getCategory());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());

        Expense savedEntity = new Expense();
        savedEntity.setId(1L);
        savedEntity.setAmount(dto.getAmount());
        savedEntity.setCategory(dto.getCategory());
        savedEntity.setDate(dto.getDate());
        savedEntity.setDescription(dto.getDescription());

        ExpenseResponseDTO response = new ExpenseResponseDTO();
        response.setId(savedEntity.getId());
        response.setAmount(savedEntity.getAmount());
        response.setCategory(savedEntity.getCategory());
        response.setDescription(savedEntity.getDescription());

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername(username);

        // When
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(response);

        // Then
        ExpenseResponseDTO result = service.addExpense(dto, username);

        verify(userRepository).findByUsername(username);
        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
        verify(mapper).toDto(savedEntity);

        assertEquals(response.getId(), result.getId());
        assertEquals(response.getAmount(), result.getAmount());
        assertEquals(response.getCategory(), result.getCategory());
        assertEquals(response.getDescription(), result.getDescription());
    }
}
