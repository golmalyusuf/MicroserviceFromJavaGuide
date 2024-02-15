package net.javaguides.microservice.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
