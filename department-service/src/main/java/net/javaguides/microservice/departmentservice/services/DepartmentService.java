package net.javaguides.microservice.departmentservice.services;

import net.javaguides.microservice.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDeparmentByCode(String departmentCode);
}
