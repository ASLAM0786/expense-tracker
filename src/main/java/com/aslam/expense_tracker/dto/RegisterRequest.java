package com.aslam.expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 4, message = "name should be at-least 4 character")
    private String name;

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 5,message = "Password must be at-least 5 character")
    private String password;

    @NotBlank
    @Size(min = 4,message = "Minimum Role at-least have 4 character")
    private String role;
}
