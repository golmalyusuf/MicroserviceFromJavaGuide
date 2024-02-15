package net.javaguides.microservice.employeeservice.repository;

import net.javaguides.microservice.employeeservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
