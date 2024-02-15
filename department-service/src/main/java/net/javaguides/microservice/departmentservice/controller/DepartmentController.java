package net.javaguides.microservice.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.javaguides.microservice.departmentservice.dto.DepartmentDto;
import net.javaguides.microservice.departmentservice.exception.ErrorDetails;
import net.javaguides.microservice.departmentservice.exception.ResourceNotFound;
import net.javaguides.microservice.departmentservice.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }

    @GetMapping("get/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentByDepartmentCode(@PathVariable String departmentCode){
        DepartmentDto deparmentByCode = departmentService.getDeparmentByCode(departmentCode);
        return new ResponseEntity<>(deparmentByCode, HttpStatus.OK);
    }

    //Handler for contronller based exception handler
   /* @ExceptionHandler(ResourceNotFound.class)
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
