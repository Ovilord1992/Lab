package com.example.lab.lab.repository;

import com.example.lab.lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String name);
    Boolean existsByUserName(String name);
}
