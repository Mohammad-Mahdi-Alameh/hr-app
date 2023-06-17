package com.example.hrApplication.models.department;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.hrApplication.models.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data // implement setters , getters , and contructors
@Entity
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "location" }))
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Employee> employees;

}
