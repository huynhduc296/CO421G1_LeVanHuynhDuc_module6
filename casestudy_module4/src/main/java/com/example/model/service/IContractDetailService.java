package com.example.model.service;

import com.example.model.entity.contract.AttachService;
import com.example.model.entity.contract.Contract;
import com.example.model.entity.contract.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContractDetailService {
    Iterable<ContractDetail> findAll();
    Optional<ContractDetail> findById(Long id);
    void save(ContractDetail contractDetail);
    List<AttachService> listAttachService();
    Page<ContractDetail> findAll(String keyWord, Pageable pageable);
    void remove(Long id);
    Page<ContractDetail> findAllByContractId(Long id,Pageable pageable);
}
