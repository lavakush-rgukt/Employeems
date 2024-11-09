package com.crud.example.controller;

import com.crud.example.advice.EmployeeNameValiddator;
import com.crud.example.model.Employee;
import com.crud.example.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> saveEmployee(@RequestBody Employee e) throws EmployeeNameValiddator {
        Employee savedEmployee = employeeService.saveEmployee(e);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllEmployee(@RequestParam(defaultValue="0") int page,
                                         @RequestParam(defaultValue = "10") int size){

        Pageable pagable = PageRequest.of(page,size, Sort.by("name").ascending());
        Page<Employee> listOfEmployee=employeeService.getAllEmployees(pagable);
        return new ResponseEntity<>(listOfEmployee,HttpStatus.OK);
    }

    @GetMapping("{empid}")
    public ResponseEntity<?> getEmpById(@PathVariable Long empid){
        Optional<Employee> newEmployee = employeeService.getEmployeeByid(empid);
        return new ResponseEntity<>(newEmployee,HttpStatus.OK);
    }
}
