package com.aslam.expense_tracker.controller;

import com.aslam.expense_tracker.dto.AuthRequest;
import com.aslam.expense_tracker.dto.RegisterRequest;
import com.aslam.expense_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
