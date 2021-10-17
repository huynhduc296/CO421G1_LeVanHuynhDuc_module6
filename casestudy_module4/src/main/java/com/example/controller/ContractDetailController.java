package com.example.controller;

import com.example.model.entity.contract.AttachService;
import com.example.model.entity.contract.Contract;
import com.example.model.entity.contract.ContractDetail;
import com.example.model.service.IContractDetailService;
import com.example.model.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "details")
public class ContractDetailController {
    @Autowired
    private IContractDetailService contractDetailService;
    @Autowired
    private IContractService contractService;

    @ModelAttribute("contracts")
    public Iterable<Contract> contractLists(){
        return contractService.findAll();
    }

    @ModelAttribute("attachServices")
    public List<AttachService> attachServices(){
        return contractDetailService.listAttachService();
    }

    @GetMapping(value = "create")
    public String showFormCreate(Model model){
        model.addAttribute("contractDetail",new ContractDetail());
        return "contractDetail/create";
    }

    @PostMapping(value = "create")
    public String createContractDetail(@ModelAttribute ContractDetail contractDetail,
                                       Model model){
        this.contractDetailService.save(contractDetail);
        model.addAttribute("msg","create successfully");
        return "contractDetail/create";
    }
}
