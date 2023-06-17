package com.example.hrApplication.models.leave;

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

@Entity
@Data // implement setters , getters , and contructors
@Table(name = "leaves")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String fromDate;

    @NotBlank
    @Size(max = 30)
    private String toDate;

    private int numberbOfDays;

    @NotBlank
    @Size(max = 300)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "leave_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LeaveType leaveType;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;
}