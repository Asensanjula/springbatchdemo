package com.pappzi.springbatchdemo.dto;

import lombok.Data;

@Data
public class EmployeeDto {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
