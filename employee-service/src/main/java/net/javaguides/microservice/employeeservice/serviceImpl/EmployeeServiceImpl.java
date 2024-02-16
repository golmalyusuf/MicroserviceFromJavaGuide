package net.javaguides.microservice.employeeservice.serviceImpl;

import lombok.AllArgsConstructor;
import net.javaguides.microservice.employeeservice.dto.APIResponseDto;
import net.javaguides.microservice.employeeservice.dto.DepartmentDto;
import net.javaguides.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.microservice.employeeservice.entities.Employee;
import net.javaguides.microservice.employeeservice.exception.ResourceNotFound;
import net.javaguides.microservice.employeeservice.repository.EmployeeRepository;
import net.javaguides.microservice.employeeservice.servuce.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    private RestTemplate restTemplate;

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
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Employee", "id", Long.toString(id))
        );
        /*EmployeeDto employeeDtoBuild = EmployeeDto.builder().id(employee.getId())
                .emailAddress(employee.getEmailAddress())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .build();*/
        System.out.println("  53 Line No "+"localhost:8080/api/departments/get/" + employee.getDepartmentCode());
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/get/" + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        //employee.setDepartmentCode(departmentDto.getDepartmentCode());
        EmployeeDto employeeDtoBuild = modelMapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto =  APIResponseDto.builder()
                .employeeDto(employeeDtoBuild)
                .departmentDto(departmentDto).build();

        return apiResponseDto;
    }
}
