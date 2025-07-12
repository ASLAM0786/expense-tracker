package com.aslam.expense_tracker.mapper;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.model.Expense;
import com.aslam.expense_tracker.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class ExpenseMapperTest {

    private final ExpenseMapper mapper = Mappers.getMapper(ExpenseMapper.class);

    @Test
    void testToEntity() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(50.0);
        dto.setCategory("Food");
        dto.setDate(LocalDate.of(2025, 1, 1));
        dto.setDescription("Lunch");
        dto.setUserId(100L); // NEW FIELD

        Expense entity = mapper.toEntity(dto);

        assertEquals(50.0, entity.getAmount());
        assertEquals("Food", entity.getCategory());
        assertEquals(LocalDate.of(2025, 1, 1), entity.getDate());
        assertEquals("Lunch", entity.getDescription());
        assertNotNull(entity.getUser());
        assertEquals(100L, entity.getUser().getId());
    }

    @Test
    void testToDto() {
        User user = new User();
        user.setId(200L);
        user.setName("Test User");

        Expense entity = new Expense();
        entity.setId(10L);
        entity.setAmount(70.0);
        entity.setCategory("Travel");
        entity.setDate(LocalDate.of(2025, 2, 2));
        entity.setDescription("Bus fare");
        entity.setUser(user);

        ExpenseResponseDTO dto = mapper.toDto(entity);

        assertEquals(10L, dto.getId());
        assertEquals(70.0, dto.getAmount());
        assertEquals("Travel", dto.getCategory());
        assertEquals("Bus fare", dto.getDescription());
        assertEquals(200L, dto.getUserId());
    }
}
