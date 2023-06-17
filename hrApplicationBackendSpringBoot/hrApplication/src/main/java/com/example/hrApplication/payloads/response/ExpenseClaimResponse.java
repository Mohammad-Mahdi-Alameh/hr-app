package com.example.hrApplication.payloads.response;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;

public class ExpenseClaimResponse {
    public ExpenseClaimResponse(Long id, Long employeeId, String employeeUserName, String date, int total,
            String description, String status, List<ExpenseClaimDetailResponse> expenseClaimDetails) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeUserName = employeeUserName;
        this.date = date;
        this.total = total;
        this.description = description;
        this.status = status;
        this.expenseClaimDetails = expenseClaimDetails;
    }
    private Long id;

    private Long employeeId;

    private String employeeUserName;

    private String date;

    private int total;

    private String description;

    private String status;

    private List<ExpenseClaimDetailResponse> expenseClaimDetails;

    public List<ExpenseClaimDetailResponse> getExpenseClaimDetails() {
        return expenseClaimDetails;
    }

    public void setExpenseClaimDetails(List<ExpenseClaimDetailResponse> expenseClaimDetails) {
        this.expenseClaimDetails = expenseClaimDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
