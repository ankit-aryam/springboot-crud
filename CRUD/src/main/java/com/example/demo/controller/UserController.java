package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 🔸 Create User
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDto) {
        UserResponseDTO created = userService.createUser(userDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 🔸 Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        UserResponseDTO user = userService.getUserByID(id);
        return ResponseEntity.ok(user);
    }

    // 🔸 Get All Users
    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(required = false) String role,            // new
            @RequestParam(required = false) Boolean active
    ) {

        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        //List<UserResponseDTO> users = userService.getAllUsers(pageable);
        List<UserResponseDTO> users = userService.getFilteredUsers(role, active, pageable);
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(
                "Users fetched successfully",
                true,
                users

        );
        return ResponseEntity.ok(response);
    }

    // 🔸 Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserRequestDTO userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    // 🔸 Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
