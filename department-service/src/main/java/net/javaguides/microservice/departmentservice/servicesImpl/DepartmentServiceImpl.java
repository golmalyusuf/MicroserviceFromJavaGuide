package net.javaguides.microservice.departmentservice.servicesImpl;

import lombok.AllArgsConstructor;
import net.javaguides.microservice.departmentservice.dto.DepartmentDto;
import net.javaguides.microservice.departmentservice.entities.Department;
import net.javaguides.microservice.departmentservice.exception.ResourceNotFound;
import net.javaguides.microservice.departmentservice.repositories.DepartmentRepository;
import net.javaguides.microservice.departmentservice.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;
    
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //Department department = new Department(departmentDto.getId(), departmentDto.getDepartmentName(), departmentDto.getDepartmentDescription(), departmentDto.getDepartmentCode());
        /*Department department = Department.builder().departmentName(departmentDto.getDepartmentName())
                .departmentDescription(departmentDto.getDepartmentDescription())
                .departmentCode(departmentDto.getDepartmentCode()).build();*/
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);
        /*DepartmentDto departmentDtoBuild = DepartmentDto.builder()
                .id(savedDepartment.getId())
                .departmentName(savedDepartment.getDepartmentName())
                .departmentDescription(savedDepartment.getDepartmentDescription())
                .departmentCode(savedDepartment.getDepartmentCode()).build();*/
        DepartmentDto departmentDtoBuild = modelMapper.map(savedDepartment, DepartmentDto.class);
        return departmentDtoBuild;
    }

    @Override
    public DepartmentDto getDeparmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
                () -> new ResourceNotFound("Department by code", "departmentCode", departmentCode)
        );
        /*DepartmentDto departmentDtoBuild = DepartmentDto.builder().id(byDepartmentCode.getId())
                .departmentCode(byDepartmentCode.getDepartmentCode())
                .departmentDescription(byDepartmentCode.getDepartmentDescription())
                .departmentName(byDepartmentCode.getDepartmentName()).build();*/
        DepartmentDto departmentDtoBuild = modelMapper.map(department, DepartmentDto.class);

        return departmentDtoBuild;
    }

}
