package com.aslam.expense_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users") // Avoid reserved keyword
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 4, message = "name should be at-least 4 character")
    private String name;

    @Email
    private String email;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 6,message = "Password must be at-least 6 character")
    private String password;

    private String role = "USER";

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
