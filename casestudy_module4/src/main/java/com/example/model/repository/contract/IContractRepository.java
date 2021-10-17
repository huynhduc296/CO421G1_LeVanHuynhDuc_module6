package com.example.model.repository.contract;

import com.example.model.entity.contract.Contract;
import com.example.model.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IContractRepository extends JpaRepository<Contract,Long> {
    @Query(value = "select * from contract ct join customer c on ct.customer_id = c.customer_id " +
            "where c.customer_name like %?1% and ct.flag=1 ", nativeQuery = true)
    Page<Contract> findAllByKeyWord(String keyWord, Pageable pageable);
}
