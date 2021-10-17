package com.example.model.repository.service;

import com.example.model.entity.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends JpaRepository<Service,Long> {
    Page<Service> findByServiceNameContaining(String name, Pageable pageable);
    Page<Service> findAll(Pageable pageable);
}
