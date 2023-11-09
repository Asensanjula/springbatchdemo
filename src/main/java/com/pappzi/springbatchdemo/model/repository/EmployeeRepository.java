package com.pappzi.springbatchdemo.model.repository;

import com.pappzi.springbatchdemo.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {



}
