package net.javaguides.microservice.employeeservice.servuce;

import net.javaguides.microservice.employeeservice.dto.APIResponseDto;
import net.javaguides.microservice.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);
    public APIResponseDto getEmployeeById(Long id);
}
