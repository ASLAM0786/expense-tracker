package com.aslam.expense_tracker.service.impl;

import com.aslam.expense_tracker.dto.AuthRequest;
import com.aslam.expense_tracker.dto.AuthResponse;
import com.aslam.expense_tracker.dto.RegisterRequest;
import com.aslam.expense_tracker.model.User;
import com.aslam.expense_tracker.repository.UserRepository;
import com.aslam.expense_tracker.security.JwtUtil;
import com.aslam.expense_tracker.service.AuthService;
import com.aslam.expense_tracker.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }
}
