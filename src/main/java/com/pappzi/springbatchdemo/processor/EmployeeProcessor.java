package com.pappzi.springbatchdemo.processor;


import com.pappzi.springbatchdemo.dto.EmployeeDto;
import com.pappzi.springbatchdemo.model.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeProcessor  implements ItemProcessor<EmployeeDto, Employee> {

    private ExecutionContext executionContext;

    public EmployeeProcessor(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }

    @Override
    public Employee process(EmployeeDto employeeDto) throws Exception {

        log.info("Inside Processor : {} {} ", employeeDto.getEmployeeId() , employeeDto.getFirstName());
        Employee employee = new Employee();
        employee. setEmployeeId(employeeDto.getEmployeeId() + executionContext.getString("customFileName"));
        employee.setFirstName( employeeDto.getFirstName() );
        employee.setLastName( employeeDto.getLastName() );
        employee.setEmail( employeeDto.getEmail() );
        employee.setAge( employeeDto.getAge() );

        return employee;
    }
}
