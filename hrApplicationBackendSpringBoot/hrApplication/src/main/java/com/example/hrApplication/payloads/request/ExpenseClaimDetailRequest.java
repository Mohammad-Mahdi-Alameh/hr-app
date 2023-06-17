package com.example.hrApplication.payloads.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ExpenseClaimDetailRequest {
    @NotBlank
    @Size(max = 30)
    private String date;

    private int total;

    private Long expenseClaimId;

    @NotBlank
    @Size(max = 30)
    private String type;

    @NotBlank
    @Size(max = 300)
    private String description;
    
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

    public Long getExpenseClaimId() {
        return expenseClaimId;
    }

    public void setExpenseClaimId(Long expenseClaimId) {
        this.expenseClaimId = expenseClaimId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
