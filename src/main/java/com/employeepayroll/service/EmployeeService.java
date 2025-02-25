package com.employeepayroll.service;

import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import com.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //save the employee
    public Employee addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());
        return employeeRepository.save(employee);
    }

    //To get all the employee
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    public Optional<Employee>getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }
    //Update the employee
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO){
        Optional<Employee>optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
              Employee employee = optionalEmployee.get();
//            employee.setName(updatedEmployee.getName());
//            employee.setDepartment(updatedEmployee.getDepartment());
//            employee.setSalary(updatedEmployee.getSalary());
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDepartment(employeeDTO.getDepartment());
            return employeeRepository.save(employee);

        }
        else{
            return null;
        }
    }

    //Delete employee
    public void deleteEmployee(Long id){
       employeeRepository.deleteById(id);
    }

}
