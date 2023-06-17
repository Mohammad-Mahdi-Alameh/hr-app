package com.example.hrApplication.models.expenseClaim;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.hrApplication.models.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data // implement setters , getters , and contructors
@Entity
@Table(name = "expense_claims")
public class ExpenseClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String date;

    private int total;

    @NotBlank
    @Size(max = 300)
    private String description;

    @NotBlank
    @Size(max = 30)
    private String status;

    @OneToMany(mappedBy = "expenseClaim", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private List<ExpenseClaimDetail> expenseClaimDetails;

    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;
}
