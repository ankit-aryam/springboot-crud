package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser (UserRequestDTO userDTO);
    UserResponseDTO getUserByID(int id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(int id, UserRequestDTO userDTO);
    void deleteUser(int id);
}
