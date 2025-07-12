package com.aslam.expense_tracker.config;

import com.aslam.expense_tracker.security.JwtUtil;
import com.aslam.expense_tracker.service.ExpenseService;
import com.aslam.expense_tracker.service.UserDetailsService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class MockedServiceConfig {

    @Bean
    public ExpenseService expenseService() {
        return mock(ExpenseService.class);
    }

    @Bean
    public JwtUtil jwtUtil() {
        JwtUtil mock = mock(JwtUtil.class);
        when(mock.validateToken(anyString())).thenReturn(true);
        return mock;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("USER")
                .build();
    }
}