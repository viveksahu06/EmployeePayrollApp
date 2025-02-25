package com.employeepayroll.controller;

import com.employeepayroll.dto.EmployeeDTO;
import com.employeepayroll.model.Employee;
import com.employeepayroll.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    //To get all the employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        log.info("All employee list endpoint called");

        List<EmployeeDTO> employees = employeeService.getAllEmployee();
        if (employees.isEmpty()) {
            log.warn("No employees found");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    //TO get employee with id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<EmployeeDTO>> getEmployeeById(@PathVariable Long id){
        log.info("specific employee list end point called");
        Optional<EmployeeDTO> employeeDTO=employeeService.getEmployeeById(id);
        if(employeeDTO.isEmpty()){
            log.warn("No employee found with this " + id + " id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result){
        if(result.hasErrors()){

            return ResponseEntity.badRequest().build();
        }
        log.info("member created");
        EmployeeDTO employeeDTO1 = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(employeeDTO1);
    }

    //Update the employee
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDTO employeeDTO,BindingResult result) {
        log.info("Employee updation endpoint called for ID: {}", id);
        if(result.hasErrors()){

            return ResponseEntity.badRequest().build();
        }

        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            log.warn("Employee not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    //Delete the employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }


}
