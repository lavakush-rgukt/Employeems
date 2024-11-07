package com.crud.example.service;

import com.crud.example.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee e);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeByid(Long empid);
}
