package com.pappzi.springbatchdemo.writer;

import com.pappzi.springbatchdemo.model.entity.Employee;
import com.pappzi.springbatchdemo.model.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EmployeeWriter  implements ItemWriter<Employee> {


    private EmployeeRepository employeeRepository;

    public EmployeeWriter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void write(List<? extends Employee> employees) throws Exception {

        employeeRepository.saveAll(employees);
        log.info("{} items saved ", employees.size());
    }
}
