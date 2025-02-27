package com.employeepayroll.service;

import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    Optional<EmployeeDTO> getEmployeeById(Long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    ResponseEntity<String> deleteEmployee(Long id);
    List<EmployeeDTO> findByDepartment(String department);
}
