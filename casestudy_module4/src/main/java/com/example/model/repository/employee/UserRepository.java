package com.example.model.repository.employee;

import com.example.model.entity.employee.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
