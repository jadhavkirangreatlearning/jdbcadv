package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee){
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Done successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword){
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyusinganyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByUsingAnyInput(@PathVariable String input){
        return ResponseEntity.ok(employeeServiceImpl.getDataByUsingAnyInput(input));
    }

    @GetMapping("/searchbyname/{empName}")
    public ResponseEntity<List<Employee>> searchByName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpName().equals(empName)).collect(Collectors.toList()));

    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpContactNumber()==empContactNumber).collect(Collectors.toList()).get(0));

    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<Employee> getDataByEmpEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()).get(0));
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));

    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));

    }


    @GetMapping("/sortbyage")
    public ResponseEntity<List<Employee>> sortByAge(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpAge)).collect(Collectors.toList()));

    }
    @GetMapping("/sortbygender")
    public ResponseEntity<List<Employee>> sortByGender(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpGender)).collect(Collectors.toList()));


    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpSalary()>=empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>> sortByDOB(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));

    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee){
        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("Data Updated Successfully");

    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }
}
