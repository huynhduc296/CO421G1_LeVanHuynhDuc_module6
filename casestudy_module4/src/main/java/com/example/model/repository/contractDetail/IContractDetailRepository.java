package com.example.model.repository.contractDetail;

import com.example.model.entity.contract.ContractDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IContractDetailRepository extends JpaRepository<ContractDetail,Long> {
    @Query(value = "select * from contract_detail cd where cd.contract_detail_id = ?1", nativeQuery = true)
    Page<ContractDetail> findAllByContractId(Long id, Pageable pageable);
}
