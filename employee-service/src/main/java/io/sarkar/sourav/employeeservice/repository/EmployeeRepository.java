package io.sarkar.sourav.employeeservice.repository;

import io.sarkar.sourav.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
