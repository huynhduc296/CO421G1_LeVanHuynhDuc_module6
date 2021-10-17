package com.example.model.service.impl;

import com.example.model.entity.contract.Contract;
import com.example.model.entity.contract.ContractDetail;
import com.example.model.repository.contract.IContractRepository;
import com.example.model.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Service
public class ContractServiceImpl implements IContractService {
    @Autowired
    IContractRepository contractRepository;
    @Override
    public Iterable<Contract> findAll() {
        return this.contractRepository.findAll();
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return this.contractRepository.findById(id);
    }

    @Override
    public void save(Contract contract) {
        this.contractRepository.save(contract);
    }

    @Override
    public Page<Contract> findAll(String keyWord, Pageable pageable) {
        Page<Contract> contractPage = this.contractRepository.findAllByKeyWord(keyWord, pageable);
        try{
            for(Contract con : contractPage){
                Date sDate= new SimpleDateFormat("yyyy-MM-dd").parse(con.getContractStartDate());
                Date eDate= new SimpleDateFormat("yyyy-MM-dd").parse(con.getContractEndDate());
                Double totalMoney = eDate.compareTo(sDate)*con.getService().getCost();
                for (ContractDetail cd : con.getContractDetailSet()){
                    totalMoney+=cd.getQuantity()*cd.getAttachService().getCost();
                }
                con.setContractTotalMoney(totalMoney.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return contractPage;
    }

    @Override
    public void remove(Long id) {
        Contract contract = this.findById(id).get();
        contract.setFlag(false);
        this.contractRepository.save(contract);
    }

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }
}
