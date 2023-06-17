package com.example.hrApplication.payloads.response;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpenseClaimDetailResponse {
    private Long id;

    private Long expenseClaimId;

    private Long expenseClaimTypeId;

    private String expenseClaimTypeName;
    
    private String date;

    private int total;

    private String description;

    public ExpenseClaimDetailResponse(Long id, Long expenseClaimId, Long expenseClaimTypeId, String expenseClaimTypeName,
            String date, int total, String description) {
        this.id = id;
        this.expenseClaimId = expenseClaimId;
        this.expenseClaimTypeId = expenseClaimTypeId;
        this.expenseClaimTypeName = expenseClaimTypeName;
        this.date = date;
        this.total = total;
        this.description = description;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseClaimId() {
        return expenseClaimId;
    }

    public void setExpenseClaimId(Long expenseClaimId) {
        this.expenseClaimId = expenseClaimId;
    }

    public Long getExpenseClaimTypeId() {
        return expenseClaimTypeId;
    }

    public void setExpenseClaimTypeId(Long expenseClaimTypeId) {
        this.expenseClaimTypeId = expenseClaimTypeId;
    }

    public String getExpenseClaimTypeName() {
        return expenseClaimTypeName;
    }

    public void setExpenseClaimTypeName(String expenseClaimTypeName) {
        this.expenseClaimTypeName = expenseClaimTypeName;
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

}
