package com.example.model.service;

import com.example.model.entity.employee.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Iterable<Employee> findAll();
    Optional<Employee> findById(Long id);
    void save(Employee employee);
    void remove(Long id);
    List<Division> listDivision();
    List<User> listUser();
    List<Education> listEducation();
    List<Position> listPosition();
    Page<Employee> findAllByNameContaining(String name, Pageable pageable);
    Page<Employee> findAll(Pageable pageable);
}
