package com.employeepayroll.controller;

import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import com.employeepayroll.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    //To get all the employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        log.info("All employee list end point called");
        return employeeService.getAllEmployee();
    }

    //TO get employee with id
    @GetMapping("/get/{id}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        log.info("specific employee list end point called");
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
        log.info("member created");
        return employeeService.addEmployee(employeeDTO);
    }

    //Update the employee
    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        log.info("Employee updation endpoint called");
        return employeeService.updateEmployee(id,employeeDTO);
    }

    //Delete the employee
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id){
        log.info("employee deletion endpoint called");
        employeeService.deleteEmployee(id);
    }

}
