package com.employeepayroll.service;

import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import com.employeepayroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save the employee and return DTO
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding employee: {}", employeeDTO);

        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee saved with ID: {}", savedEmployee.getId());
        return convertToDTO(savedEmployee);
    }

    // Get all employees as DTOs (without streams)
    public List<EmployeeDTO> getAllEmployee() {
        log.info("Fetching all employees...");
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOs.add(convertToDTO(employee));
        }

        return employeeDTOs;
    }

    // Get employee by ID as DTO
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return Optional.of(convertToDTO(optionalEmployee.get()));
        } else {
            return Optional.empty();
        }
    }

    // Update employee and return DTO
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDepartment(employeeDTO.getDepartment());

            Employee updatedEmployee = employeeRepository.save(employee);
            log.info("Employee updated with ID: {}", id);
            return convertToDTO(updatedEmployee);
        } else {
            log.error("Employee not found with ID: {}", id);
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    // Delete employee
    public String deleteEmployee(Long id) {
        log.warn("Deleting employee with ID: {}", id);
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            log.info("Employee deleted with ID: {}", id);
            return "Employee with id " + id + " deleted successful";
        } else {
            log.error("Employee not found with ID: {}", id);
            return "No employee found with this id " + id +" ";
        }
    }

    // Convert Employee to EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return new EmployeeDTO(employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    // Convert EmployeeDTO to Employee
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());
        return employee;
    }
}
