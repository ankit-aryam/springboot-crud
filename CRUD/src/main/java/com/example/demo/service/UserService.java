package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser (UserRequestDTO userDTO);
    UserResponseDTO getUserByID(int id);
    List<UserResponseDTO> getFilteredUsers(String role, Boolean active, Pageable pageable);


    List<UserResponseDTO> getAllUsers(Pageable pageable);

    UserResponseDTO updateUser(int id, UserRequestDTO userDTO);
    void deleteUser(int id);
}
