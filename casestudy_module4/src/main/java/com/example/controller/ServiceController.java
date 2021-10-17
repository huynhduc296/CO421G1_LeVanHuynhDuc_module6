package com.example.controller;

import com.example.dto.ServiceDto;
import com.example.model.entity.service.RentType;
import com.example.model.entity.service.Service;
import com.example.model.entity.service.ServiceType;
import com.example.model.service.IService;
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
@RequestMapping(value = "services")
public class ServiceController {
    @Autowired
    private IService service;

    @ModelAttribute("rentTypes")
    public List<RentType> rentTypeList() {
        return service.listRentType();
    }

    @ModelAttribute("serviceTypes")
    public List<ServiceType> serviceTypeList() {
        return service.listServiceType();
    }

    @GetMapping(value = {""})
    public ModelAndView goList(@RequestParam("search") Optional<String> search,
                               @RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),3);
        Page<Service> services;
        ModelAndView modelAndView = new ModelAndView("service/list");
        if (search.isPresent()) {
            services = service.findAllByNameContaining(search.get(), pageable);
            modelAndView.addObject("search", search.get());
        } else {
            services = service.findAll(pageable);
        }
        modelAndView.addObject("services", services);
        return modelAndView;
    }

    @GetMapping(value = "create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("service/create", "serviceDto", new ServiceDto());
        return modelAndView;
    }

    @PostMapping(value = "create")
    public String createService(@ModelAttribute @Valid ServiceDto serviceDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        new ServiceDto().validate(serviceDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "service/create";
        }
        Service service = new Service();
        BeanUtils.copyProperties(serviceDto, service);
        this.service.save(service);
        redirectAttributes.addFlashAttribute("msg", "create service successfully");
        return "redirect:/services";
    }
    @GetMapping(value = "edit")
    public String showFormEdit(@RequestParam Long id, Model model){
        Service service = this.service.findById(id).get();
        ServiceDto serviceDto = new ServiceDto();
        BeanUtils.copyProperties(service,serviceDto);
        model.addAttribute("ServiceDto",serviceDto);
        return "service/edit";
    }

    @PostMapping(value = "edit")
    public String editService(@ModelAttribute @Valid ServiceDto serviceDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        new ServiceDto().validate(serviceDto,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "service/edit";
        }
        Service service = new Service();
        BeanUtils.copyProperties(serviceDto,service);
        this.service.save(service);
        redirectAttributes.addFlashAttribute("msg","Update customer information successfully!!!");
        return "redirect:/services";
    }


    @PostMapping(value = "delete")
    public String deleteService(@RequestParam Long id,RedirectAttributes redirectAttributes){
        this.service.remove(id);
        redirectAttributes.addFlashAttribute("msg","Delete successfully!!!");
        return "redirect:/services";

    }
}