package com.example.model.repository.employee;

import com.example.model.entity.customer.Customer;
import com.example.model.entity.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findByNameContaining(String name, Pageable pageable);
    Page<Employee> findAll(Pageable pageable);

}
