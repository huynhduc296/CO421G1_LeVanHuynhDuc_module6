package com.example.model.service.impl;

import com.example.model.entity.contract.AttachService;
import com.example.model.entity.contract.ContractDetail;
import com.example.model.repository.contractDetail.IAttachServiceRepository;
import com.example.model.repository.contractDetail.IContractDetailRepository;
import com.example.model.service.IContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractDetailServiceImpl implements IContractDetailService {
    @Autowired
    IContractDetailRepository contractDetailRepository;
    @Autowired
    IAttachServiceRepository attachServiceRepository;
    @Override
    public Iterable<ContractDetail> findAll() {
        return contractDetailRepository.findAll();
    }

    @Override
    public Optional<ContractDetail> findById(Long id) {
        return contractDetailRepository.findById(id);
    }

    @Override
    public void save(ContractDetail contractDetail) {
        contractDetailRepository.save(contractDetail);
    }

    @Override
    public List<AttachService> listAttachService() {
        return attachServiceRepository.findAll();
    }

    @Override
    public Page<ContractDetail> findAll(String keyWord, Pageable pageable) {
        return null;
    }

    @Override
    public void remove(Long id) {
        this.contractDetailRepository.deleteById(id);
    }

    @Override
    public Page<ContractDetail> findAllByContractId(Long id,Pageable pageable) {
        return contractDetailRepository.findAll(pageable);
    }
}