package com.aslam.expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 5,message = "Password must be at-least 5 character")
    private String password;
}
