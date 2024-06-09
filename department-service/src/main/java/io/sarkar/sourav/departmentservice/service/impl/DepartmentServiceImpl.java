package io.sarkar.sourav.departmentservice.service.impl;

import io.sarkar.sourav.departmentservice.dto.DepartmentDto;
import io.sarkar.sourav.departmentservice.entity.Department;
import io.sarkar.sourav.departmentservice.repository.DepartmentRepository;
import io.sarkar.sourav.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //Convert DTO to entity
        Department department = modelMapper.map(departmentDto, Department.class);

        //Save entity
        Department savedDepartment = departmentRepository.save(department);

        //Convert entity to DTO
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }
}
