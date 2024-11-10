package com.crud.example.controller;

import com.crud.example.advice.EmployeeNameValiddator;
import com.crud.example.model.Employee;
import com.crud.example.serviceImpl.EmployeeServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
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

    //multipat read and write example with apache poi
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        List<List<String>> csvData = new ArrayList<>();
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty",HttpStatus.BAD_REQUEST);
        }
        if(file.getContentType().equals("text/csv")){

            try(InputStream fis = file.getInputStream();
                Workbook wk = WorkbookFactory.create(fis);
            ){
                Sheet s = wk.getSheetAt(0);
                for(Row row:s){
                    List<String> columndata= new ArrayList<>();
                    for(Cell cell:row){
                        columndata.add(cell.getStringCellValue());
                    }
                    csvData.add(columndata);
                }
            }catch (IOException e){
                e.printStackTrace();
            }


        }
        return new ResponseEntity(csvData,HttpStatus.OK);
    }

   /* @GetMapping("/download")
    public ResponseEntity<?> download(){
        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.valueOf("text/csv"));
        h.setContentDispositionFormData("attachemnt","data.csv");

        Pageable pageable = PageRequest.of(0,10,Sort.by("name").descending());
        Page<Employee> ll = employeeService.getAllEmployees(pageable);



        return new ResponseEntity<>()
    }*/




}
