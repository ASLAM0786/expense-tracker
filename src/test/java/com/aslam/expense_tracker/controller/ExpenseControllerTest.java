package com.aslam.expense_tracker.controller;

import com.aslam.expense_tracker.dto.ExpenseRequestDTO;
import com.aslam.expense_tracker.dto.ExpenseResponseDTO;
import com.aslam.expense_tracker.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ExpenseControllerTest.MockedServiceConfig.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExpenseService expenseService;

    @Test
    void shouldCreateExpenseSuccessfully() throws Exception {
        ExpenseRequestDTO request = new ExpenseRequestDTO();
        request.setAmount(100.0);
        request.setCategory("Groceries");
        request.setDate(LocalDate.now());
        request.setDescription("Weekly shopping");

        ExpenseResponseDTO response = new ExpenseResponseDTO();
        response.setId(1L);
        response.setAmount(100.0);
        response.setCategory("Groceries");
        response.setDescription("Weekly shopping");

        when(expenseService.addExpense(any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.category").value("Groceries"));
    }

    @TestConfiguration
    static class MockedServiceConfig {
        @Bean
        public ExpenseService expenseService() {
            return mock(ExpenseService.class);
        }
    }
}
