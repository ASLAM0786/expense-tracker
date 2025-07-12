package com.aslam.expense_tracker.service;

import com.aslam.expense_tracker.dto.AuthRequest;
import com.aslam.expense_tracker.dto.AuthResponse;
import com.aslam.expense_tracker.dto.RegisterRequest;

public interface AuthService {
    public AuthResponse register(RegisterRequest registerRequest);
    public AuthResponse login(AuthRequest request);
}
