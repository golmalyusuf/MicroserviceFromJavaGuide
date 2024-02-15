package net.javaguides.microservice.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.microservice.employeeservice.exception.ErrorDetails;
import net.javaguides.microservice.employeeservice.exception.ResourceNotFound;
import net.javaguides.microservice.employeeservice.servuce.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }
    /*this method is for handling local custom exceptions*/
    /*@ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound resourceNotFound, WebRequest webRequest){
        ErrorDetails userNotFound = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFound.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"

        );
        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }*/
}
