package com.employeepayroll.controller;

import com.employeepayroll.model.Employee;
import com.employeepayroll.service.EmployeeService;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    Logger logger = Logger.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;
    //To get all the employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        logger.info("All employee list end point called");
        return employeeService.getAllEmployee();
    }

    //TO get employee with id
    @GetMapping("/get/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id){
        logger.info("specific employee list end point called");
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public Employee addEmployee(@RequestBody Employee employee){
        logger.info("member created");
        return employeeService.addEmployee(employee);
    }

    //Update the employee
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        logger.info("Employee updation endpoint called");
        return employeeService.updateEmployee(id,employee);
    }

    //Delete the employee
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id){
        logger.info("employee deletion endpint called");
        employeeService.deleteEmployee(id);
    }

}
