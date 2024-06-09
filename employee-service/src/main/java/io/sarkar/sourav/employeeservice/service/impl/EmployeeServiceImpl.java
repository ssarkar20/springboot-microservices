package io.sarkar.sourav.employeeservice.service.impl;

import io.sarkar.sourav.employeeservice.dto.APIResponseDto;
import io.sarkar.sourav.employeeservice.dto.DepartmentDto;
import io.sarkar.sourav.employeeservice.dto.EmployeeDto;
import io.sarkar.sourav.employeeservice.entity.Employee;
import io.sarkar.sourav.employeeservice.repository.EmployeeRepository;
import io.sarkar.sourav.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
//    private WebClient webClient;
    private final Builder webClientBuilder;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        //Convert DTO to Entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        //Save
        Employee savedEmployee = employeeRepository.save(employee);

        //Convert Entity to DTO
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        //Get employee from database
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        //Get department from database
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = webClientBuilder.build().get()
//                .uri("http://DEPARTMENT-SERVICE/api/departments/" + "DEPT01")
                .uri("http://DEPARTMENT-SERVICE/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();


        //Prepare and return API response dto
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

//    public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
//
//        //Get employee from database
//        Employee employee = employeeRepository.findById(employeeId).get();
//        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//
//        //Get default department
//        DepartmentDto departmentDto = new DepartmentDto();
//        departmentDto.setDepartmentCode("EXCP01");
//        departmentDto.setDepartmentName("fallback");
//        departmentDto.setDepartmentDescription("circuit breaker default");
//
//        //Prepare and return API response dto
//        APIResponseDto apiResponseDto = new APIResponseDto();
//        apiResponseDto.setEmployeeDto(employeeDto);
//        apiResponseDto.setDepartmentDto(departmentDto);
//
//        return apiResponseDto;
//    }
}
