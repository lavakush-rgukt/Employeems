package com.crud.example.serviceImpl;

import com.crud.example.model.Employee;
import com.crud.example.repository.EmployeeRepository;
import com.crud.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee e) {
        return employeeRepository.save(e);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeByid(Long empid) {
        throw new NullPointerException();
        //return employeeRepository.findById(empid);
    }
}
