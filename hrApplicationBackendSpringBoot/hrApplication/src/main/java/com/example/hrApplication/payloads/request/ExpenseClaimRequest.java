package com.example.hrApplication.payloads.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ExpenseClaimRequest {
    
    @NotBlank
    private String date;

    @NotBlank
    @Size(max = 30)
    private String status;
    
    private int total;

    @NotBlank
    @Size(max = 300)
    private String description;

    private List<ExpenseClaimDetailRequest> expenseClaimDetailRequests;
    
    public List<ExpenseClaimDetailRequest> getExpenseClaimDetailRequests() {
        return expenseClaimDetailRequests;
    }

    public void setExpenseClaimDetailRequests(List<ExpenseClaimDetailRequest> expenseClaimDetailRequests) {
        this.expenseClaimDetailRequests = expenseClaimDetailRequests;
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
