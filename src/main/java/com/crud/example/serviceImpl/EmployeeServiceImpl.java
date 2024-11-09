package com.crud.example.serviceImpl;

import com.crud.example.advice.EmployeeNameValiddator;
import com.crud.example.model.Employee;
import com.crud.example.repository.EmployeeRepository;
import com.crud.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee e) throws EmployeeNameValiddator {

        if(e.getName().isEmpty()){
            throw new EmployeeNameValiddator();
        }
        return employeeRepository.save(e);

    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pagable) {
        return employeeRepository.findAll(pagable);
    }

    @Override
    public Optional<Employee> getEmployeeByid(Long empid) {
        throw new NullPointerException();
        //return employeeRepository.findById(empid);
    }
}
