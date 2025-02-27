package com.employeepayroll.repository;

import com.employeepayroll.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "select * from employee_payroll, employee_department where employee_id = id and department = :department", nativeQuery = true)
    List<Employee> findByDepartment(String department);
}
