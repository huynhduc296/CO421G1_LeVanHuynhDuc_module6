package com.example.controller;

import com.example.dto.CustomerDto;
import com.example.dto.EmployeeDto;
import com.example.model.entity.customer.Customer;
import com.example.model.entity.employee.*;
import com.example.model.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @ModelAttribute("divisions")
    public List<Division> divisions () {
        return employeeService.listDivision();
    }

    @ModelAttribute("users")
    public List<User> users () {
        return employeeService.listUser();
    }

    @ModelAttribute("positions")
    public List<Position> positions () {
        return employeeService.listPosition();
    }

    @ModelAttribute("educations")
    public List<Education> educations () {
        return employeeService.listEducation();
    }

    @GetMapping(value = {""})
    public ModelAndView goList(@RequestParam("search") Optional<String> search,
                               @RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),3);
        Page<Employee> employees;
        ModelAndView modelAndView = new ModelAndView("/employee/list");
        if (search.isPresent()) {
            employees = employeeService.findAllByNameContaining(search.get(), pageable);
            modelAndView.addObject("search", search.get());
        } else {
            employees = employeeService.findAll(pageable);
        }
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping(value = "create")
    public String showFormCreate(Model model){
        model.addAttribute("employeeDto",new EmployeeDto());
        return "employee/create";
    }

    @PostMapping(value = "create")
    public String createEmployee(@ModelAttribute @Valid EmployeeDto employeeDto,
                                 BindingResult bindingResult ,
                                 RedirectAttributes redirectAttributes){
        new EmployeeDto().validate(employeeDto,bindingResult);
        if (bindingResult.hasErrors()){
            return "employee/create";
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        this.employeeService.save(employee);
        redirectAttributes.addFlashAttribute("msg","Create new employee successfully!!!");
        return "redirect:/employees";
    }

    @GetMapping(value = "edit")
    public String showFormEdit(@RequestParam Long id,Model model){
        Employee employee = this.employeeService.findById(id).get();
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        model.addAttribute("employeeDto",employeeDto);
        return "employee/edit";
    }

    @PostMapping(value = "edit")
    public String editEmployee(@ModelAttribute @Valid EmployeeDto employeeDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        new EmployeeDto().validate(employeeDto,bindingResult);
        if (bindingResult.hasErrors()){
            return "employee/edit";
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        this.employeeService.save(employee);
        redirectAttributes.addFlashAttribute("msg","edit successfully");
        return "redirect:/employees";
    }



    @PostMapping(value = "delete")
    public String deleteCustomer(@RequestParam Long id,RedirectAttributes redirectAttributes){
        this.employeeService.remove(id);
        redirectAttributes.addFlashAttribute("msg","Delete successfully!!!");
        return "redirect:/employees";

    }
}
