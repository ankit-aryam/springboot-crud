package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message = "Name is required")
    private String username;

//    @Email(message = "Email is required")
//    private String email;
//
//    @Min(value = 1, message = "Age must be greater than 1")
//    private int age;

    private String role;
    private Boolean active;
}
