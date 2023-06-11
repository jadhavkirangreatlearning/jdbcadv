package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private int empId;

    private String empName;

    private String empAddress;

    private long empContactNumber;

    private double empSalary;

    private int empAge;

    private String empGender;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    private String empEmailId;

    private String empPassword;

}
