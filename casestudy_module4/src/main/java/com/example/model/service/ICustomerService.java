package com.example.model.service;

import com.example.model.entity.customer.Customer;
import com.example.model.entity.customer.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ICustomerService{
    Iterable<Customer> findAll();
    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void remove(Long id);
    List<CustomerType> listCustomerType();
    Page<Customer> findAllByNameContaining(String name, Pageable pageable);
    Page<Customer> findAll(Pageable pageable);

}
