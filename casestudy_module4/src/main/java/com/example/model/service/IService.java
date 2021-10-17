package com.example.model.service;

import com.example.model.entity.customer.Customer;
import com.example.model.entity.service.RentType;
import com.example.model.entity.service.Service;
import com.example.model.entity.service.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IService {
    Iterable<Service> findAll();
    Optional<Service> findById(Long id);
    void save(Service service);
    void remove(Long id);
    List<RentType> listRentType();
    List<ServiceType> listServiceType();
    Page<Service> findAllByNameContaining(String name, Pageable pageable);
    Page<Service> findAll(Pageable pageable);
}
