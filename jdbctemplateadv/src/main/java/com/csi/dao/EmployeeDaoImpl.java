package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String insert_SQL = "insert into employee(empid, empname, empaddress, empcontactnumber, empsalary, empage, empgender, empdob, empemailid, emppassword)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String select_by_id_SQL = "select * from employee where empid=?";

    String select_all_SQL = "select * from employee";

    String update_SQL = "update employee set empname=?, empaddress=?, empcontactnumber=?, empsalary=?, empage=?, empgender=?, empdob=?, empemailid=?, emppassword=? where empid=?";

    String delete_by_id_SQL = "delete from employee where empid=?";

    String delete_all_SQL = "truncate table employee";

    public Employee employee(ResultSet resultSet, int numRow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empContactNumber(resultSet.getLong(4)).empSalary(resultSet.getDouble(5)).empAge(resultSet.getInt(6)).empGender(resultSet.getString(7)).empDOB(resultSet.getDate(8)).empEmailId(resultSet.getString(9)).empPassword(resultSet.getString(10)).build();
    }

    @Override
    public void signUp(Employee employee) {

        jdbcTemplate.update(insert_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpContactNumber(), employee.getEmpSalary(), employee.getEmpAge(), employee.getEmpGender(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword());


    }

    @Override
    public boolean signIn(String empEmalId, String empPassword) {

        boolean flag = false;

        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmalId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    @Override
    public Employee getDataById(int empId) {
        return jdbcTemplate.query(select_by_id_SQL, this::employee, empId).get(0);

    }

    @Override
    public List<Employee> getAllData() {
        return jdbcTemplate.query(select_all_SQL, this::employee);
    }

    @Override
    public void updateData(int empId, Employee employee) {

        jdbcTemplate.update(update_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpContactNumber(), employee.getEmpSalary(), employee.getEmpAge(), employee.getEmpGender(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword(), empId);

    }

    @Override
    public void deleteDataById(int empId) {
        jdbcTemplate.update(delete_by_id_SQL, empId);
    }

    @Override
    public void deleteAllData() {
        jdbcTemplate.update(delete_all_SQL);
    }

    @Override
    public List<Employee> getDataByUsingAnyInput(String input) {

        List<Employee> employeeList = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


        for (Employee employee : getAllData()) {

            String dobDate = simpleDateFormat.format(employee.getEmpDOB());

            if (String.valueOf(employee.getEmpId()).equals(input)
                    || employee.getEmpName().equals(input)
                    || String.valueOf(employee.getEmpContactNumber()).equals(input)
                    || employee.getEmpEmailId().equals(input)
                    || dobDate.equals(input)) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}
