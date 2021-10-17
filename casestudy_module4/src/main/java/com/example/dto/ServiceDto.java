package com.example.dto;

import com.example.model.entity.service.RentType;
import com.example.model.entity.service.ServiceType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServiceDto implements Validator {
    private Long serviceId;
    @NotBlank
    private String serviceName;
    @NotBlank
    private String area;
    @NotNull
    private Double cost;
    @NotBlank
    private String maxPeople;
    private String standardRoom;
    private String description;
    private Double poolArea;
    private String numberFloor;
    private RentType rentType;
    private ServiceType serviceType;
    private boolean flag = true;

    public ServiceDto() {
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(Double poolArea) {
        this.poolArea = poolArea;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(String numberFloor) {
        this.numberFloor = numberFloor;
    }

    public RentType getRentType() {
        return rentType;
    }

    public void setRentType(RentType rentType) {
        this.rentType = rentType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ServiceDto serviceDto = (ServiceDto) target;

        if (!serviceDto.numberFloor.equals("")) {
            if (!serviceDto.numberFloor.matches("^[0-9]+$")) {
                errors.rejectValue("numberFloor", "service.validFloor", "Service number of floors format number");
            } else if (Integer.parseInt(serviceDto.numberFloor) <= 0) {
                errors.rejectValue("numberFloor", "service.validFloor", "Number of floors great 0");
            }
        }
    }
}