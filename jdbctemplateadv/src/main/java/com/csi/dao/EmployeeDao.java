package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmalId, String empPassword);

    public Employee getDataById(int empId);

    public List<Employee> getAllData();

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();

    public List<Employee> getDataByUsingAnyInput(String input);

}
