package com.example.hrApplication.payloads.response;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.hrApplication.models.department.Department;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

public class LeaveResponse {
     public LeaveResponse(Long id,String fromDate, String toDate, int numberOfDays, String note, String leaveType,Long employeeId,String employeeUserName) {
        this.id = id;
        this.employeeId = employeeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfDays = numberOfDays;
        this.note = note;
        this.leaveType = leaveType;
        this.employeeUserName = employeeUserName;
    }

    private Long id;

    private Long employeeId;

    private String employeeUserName;

    private String fromDate;

    private String toDate;

    private int numberOfDays;

    private String note;

    private String leaveType;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }
}