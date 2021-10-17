package com.example.controller;

import com.example.dto.ContractDto;
import com.example.model.entity.contract.Contract;
import com.example.model.entity.customer.Customer;
import com.example.model.entity.employee.Employee;
import com.example.model.entity.service.Service;
import com.example.model.service.IContractService;
import com.example.model.service.ICustomerService;
import com.example.model.service.IEmployeeService;
import com.example.model.service.IService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "contracts")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IService service;

    @ModelAttribute("customers")
    public Page<Customer> customerPage(Pageable pageable){
        return customerService.findAll(pageable);
    }
    @ModelAttribute("employees")
    public Page<Employee> employeePage(Pageable pageable){
        return employeeService.findAll(pageable);
    }
    @ModelAttribute("services")
    public Page<Service> servicePage(Pageable pageable){
        return service.findAll(pageable);
    }



    @GetMapping(value = {""})
    public ModelAndView goList(@RequestParam("search") Optional<String> search,
                               @RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),3);
        Page<Contract> contracts;
        ModelAndView modelAndView = new ModelAndView("/contract/list");
        if (search.isPresent()) {
            contracts = contractService.findAll(search.get(), pageable);
            modelAndView.addObject("search", search.get());
        } else {
            contracts = contractService.findAll(pageable);
        }
        modelAndView.addObject("contracts", contracts);
        return modelAndView;
    }


    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("contract/create");
        modelAndView.addObject("contractDto", new ContractDto());
        return modelAndView;
    }

    @PostMapping(value = "/create")
    public String createContract(@ModelAttribute @Valid ContractDto contractDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        new ContractDto().validate(contractDto,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "contract/create";
        }
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDto,contract);
        contractService.save(contract);
        redirectAttributes.addFlashAttribute("msg","Create new contract successfully!!!");
        return "contracts/create";
    }
}