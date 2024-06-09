package io.sarkar.sourav.employeeservice.service;

import io.sarkar.sourav.employeeservice.dto.APIResponseDto;
import io.sarkar.sourav.employeeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
