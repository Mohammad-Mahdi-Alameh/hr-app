package com.example.hrApplication.models.leave;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data // implement setters , getters , and contructors
@Entity
@Table(name = "leave_types", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "leaveType", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private List<Leave> leaves;

}
