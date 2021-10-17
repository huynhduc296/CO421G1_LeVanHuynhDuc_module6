package com.example.dto;

import com.example.model.entity.employee.Division;
import com.example.model.entity.employee.Education;
import com.example.model.entity.employee.Position;
import com.example.model.entity.employee.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmployeeDto implements Validator {
    private Long employeeId;
    @NotBlank
    private String name;
    @NotBlank
    private String birthday;
    @NotBlank
    private String idCard;
    @NotBlank
    private String salary;
    @NotBlank
    private String phone;
    @Email
    private String email;
    private String address;
    private User user;
    private Position position;
    private Division division;
    private Education education;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean flag = true;

    public EmployeeDto() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
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
        EmployeeDto employeeDto = (EmployeeDto) target;

        if (!employeeDto.phone.matches("^(090|091)[0-9]{7}$")) {
            errors.rejectValue("phone", "employee.validPhone", "Employee phone format 091xxxxxxx or 090xxxxxxx");
        }
        if (!employeeDto.idCard.matches("^[0-9]{9,10}$")) {
            errors.rejectValue("idCard", "employee.validIdCard", "Employee id card format XXXXXXXXX or XXXXXXXXXXXX ");
        }
        if (!employeeDto.salary.matches("^[0-9]+$")) {
            errors.rejectValue("salary", "employee.validSalary", "Employee salary format number");
        } else if (Integer.parseInt(employeeDto.salary) < 0) {
            errors.rejectValue("salary", "employee.validSalary", "Employee salary great 0");
        }
    }
}