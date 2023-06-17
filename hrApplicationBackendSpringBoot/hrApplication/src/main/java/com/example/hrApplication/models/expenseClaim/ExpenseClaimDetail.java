package com.example.hrApplication.models.expenseClaim;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data // implement setters , getters , and contructors
@Table(name = "expense_claim_details")
public class ExpenseClaimDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String date;

    private int total;
    
    @NotBlank
    @Size(max = 300)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "expense_claim_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ExpenseClaimType expenseClaimType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "expense_claim_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ExpenseClaim expenseClaim;

}