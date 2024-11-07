package com.crud.example.controller;

import com.crud.example.model.Employee;
import com.crud.example.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee e){
        Employee savedEmployee = employeeService.saveEmployee(e);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllEmployee(){
        List<Employee> listOfEmployee=employeeService.getAllEmployees();
        return new ResponseEntity<>(listOfEmployee,HttpStatus.OK);
    }

    @GetMapping("{empid}")
    public ResponseEntity<?> getEmpById(@PathVariable Long empid){
        Optional<Employee> newEmployee = employeeService.getEmployeeByid(empid);
        return new ResponseEntity<>(newEmployee,HttpStatus.OK);
    }
}
