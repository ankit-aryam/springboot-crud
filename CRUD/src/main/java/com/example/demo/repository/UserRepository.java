package com.example.demo.repository;


import com.example.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Page<UserEntity> findByRole(String role, Pageable pageable);
    Page<UserEntity> findByActive(Boolean active, Pageable pageable);
    Page<UserEntity> findByRoleAndActive(String role, Boolean active, Pageable pageable);
}
