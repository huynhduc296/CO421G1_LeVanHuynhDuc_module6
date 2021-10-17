package com.example.model.service;

import com.example.model.entity.contract.Contract;
import com.example.model.entity.customer.Customer;
import com.example.model.entity.customer.CustomerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContractService {
    Iterable<Contract> findAll();
    Optional<Contract> findById(Long id);
    void save(Contract contract);
    Page<Contract> findAll(String keyWord, Pageable pageable);
    void remove(Long id);
    Page<Contract> findAll(Pageable pageable);
}
