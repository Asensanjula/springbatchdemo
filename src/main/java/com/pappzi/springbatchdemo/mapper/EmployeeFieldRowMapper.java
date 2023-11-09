package com.pappzi.springbatchdemo.mapper;

import com.pappzi.springbatchdemo.dto.EmployeeDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeFieldRowMapper implements FieldSetMapper<EmployeeDto> {
    @Override
    public EmployeeDto mapFieldSet(FieldSet fieldSet) throws BindException {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployeeId(fieldSet.readString("employeeId"));
        employeeDto.setFirstName(fieldSet.readString("firstName"));
        employeeDto.setLastName(fieldSet.readString("lastName"));
        employeeDto.setEmail(fieldSet.readString("email"));
        employeeDto.setAge(fieldSet.readInt("age"));

        return employeeDto;

    }
}
