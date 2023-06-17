package com.example.hrApplication.payloads.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LeaveRequest {
    private String fromDate;
    
    private String toDate;
 
    private int numberOfDays;

    private String note;

    private String leaveType;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String from) {
        this.fromDate = from;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String to) {
        this.toDate = to;
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

    // public Long getEmployeeId() {
    //     return employeeId;
    // }

    // public void setEmployeeId(Long employeeId) {
    //     this.employeeId = employeeId;
    // }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveTypeName) {
        this.leaveType = leaveTypeName;
    }

}
