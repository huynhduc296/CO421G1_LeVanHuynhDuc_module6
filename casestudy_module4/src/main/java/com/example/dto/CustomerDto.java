package com.example.dto;

import com.example.model.entity.customer.CustomerType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerDto implements Validator {


    private Long customerId;
    @NotBlank(message = "please input code")
    private String customerCode;
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerBirthday;
    @NotBlank
    private String customerGender;
    @NotBlank
    private String customerIdCard;
    @NotBlank
    private String customerPhone;
    @Email
    private String customerEmail;

    private String customerAddress;
    private CustomerType customerType;
    private boolean flag = true;

    public CustomerDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
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
        CustomerDto customerDto = (CustomerDto) target;
        if (!customerDto.customerCode.matches("^KH-[0-9]{4}$")) {
            errors.rejectValue("customerCode", "customer.validCode", "Customer code format KH-XXXX");
        }
        if (!customerDto.customerPhone.matches("^(090|091)[0-9]{7}$")) {
            errors.rejectValue("customerPhone", "customer.validPhone", "Customer phone format 091xxxxxxx or 090xxxxxxx");
        }
        if (!customerDto.customerIdCard.matches("^[0-9]{9,10}$")) {
            errors.rejectValue("customerIdCard", "customer.validIdcard", "Customer id card format XXXXXXXXX or XXXXXXXXXXXX ");
        }
    }
}
