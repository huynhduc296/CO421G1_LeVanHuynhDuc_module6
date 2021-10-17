package com.example.model.service.impl;


import com.example.model.entity.service.RentType;
import com.example.model.entity.service.Service;
import com.example.model.entity.service.ServiceType;
import com.example.model.repository.service.IRentTypeRepository;
import com.example.model.repository.service.IServiceRepository;
import com.example.model.repository.service.IServiceTypeRepository;
import com.example.model.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceImpl implements IService {
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IServiceTypeRepository serviceTypeRepository;
    @Autowired
    private IRentTypeRepository rentTypeRepository;

    @Override
    public Iterable<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public void save(Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void remove(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<RentType> listRentType() {
        return this.rentTypeRepository.findAll();
    }

    @Override
    public List<ServiceType> listServiceType() {
        return this.serviceTypeRepository.findAll();
    }

    @Override
    public Page<Service> findAllByNameContaining(String name, Pageable pageable) {
        return serviceRepository.findByServiceNameContaining(name,pageable);
    }

    @Override
    public Page<Service> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }
}
