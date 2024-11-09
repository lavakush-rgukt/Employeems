package com.crud.example.service;

import com.crud.example.advice.EmployeeNameValiddator;
import com.crud.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee e) throws EmployeeNameValiddator;

    Page<Employee> getAllEmployees(Pageable pagable);

    Optional<Employee> getEmployeeByid(Long empid);
}
