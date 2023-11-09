package com.pappzi.springbatchdemo.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
