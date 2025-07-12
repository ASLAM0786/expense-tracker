package com.aslam.expense_tracker.controller;


import com.aslam.expense_tracker.config.MockedServiceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;


import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExpenseController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(MockedServiceConfig.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateExpenseSuccessfully() throws Exception {
        Principal mockPrincipal = () -> "aslam123";

        mockMvc.perform(post("/api/v1/expense")
                        .principal(mockPrincipal) // ✅ inject Principal here
                        .contentType("application/json")
                        .content("""
                    {
                      "amount": 100,
                      "category": "Food",
                      "date": "2025-07-12",
                      "description": "Lunch",
                      "userId": 1
                    }
                    """))
                .andExpect(status().isCreated());
    }
}