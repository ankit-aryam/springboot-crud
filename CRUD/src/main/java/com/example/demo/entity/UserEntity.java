package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Table(name = "newusers")
    public class UserEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

//        @NotBlank(message = "Name is required")
//        private String name;

//        @Email(message = "Email should be valid")
//        private String email;

//       @Min(value = 1, message = "Age must be greater than 1")
//        private int age;

        @Column(nullable = false, unique = true)
        private String username;

        @Column
        private String password;

        @Column
        private String role;

        @Column
        private Boolean active;

    }

