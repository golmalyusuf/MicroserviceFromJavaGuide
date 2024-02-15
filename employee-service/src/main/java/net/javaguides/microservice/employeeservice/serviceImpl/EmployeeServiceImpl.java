package net.javaguides.microservice.employeeservice.serviceImpl;

import lombok.AllArgsConstructor;
import net.javaguides.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.microservice.employeeservice.entities.Employee;
import net.javaguides.microservice.employeeservice.exception.ResourceNotFound;
import net.javaguides.microservice.employeeservice.repository.EmployeeRepository;
import net.javaguides.microservice.employeeservice.servuce.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        /*Employee employeeBuild = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .emailAddress(employeeDto.getEmailAddress()).build();*/
        Employee employeeBuild = modelMapper.map(employeeDto, Employee.class);
        Employee employeeSaved = employeeRepository.save(employeeBuild);
        /*EmployeeDto employeeDtoBuild = EmployeeDto.builder().id(employeeSaved.getId())
                .firstName(employeeSaved.getFirstName())
                .lastName(employeeSaved.getLastName())
                .emailAddress(employeeSaved.getEmailAddress())
                .build();*/
        EmployeeDto employeeDtoBuild = modelMapper.map(employeeSaved, EmployeeDto.class);
        return employeeDtoBuild;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Employee", "id", Long.toString(id))
        );
        /*EmployeeDto employeeDtoBuild = EmployeeDto.builder().id(employee.getId())
                .emailAddress(employee.getEmailAddress())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .build();*/
        EmployeeDto employeeDtoBuild = modelMapper.map(employee, EmployeeDto.class);
        return employeeDtoBuild;
    }
}
