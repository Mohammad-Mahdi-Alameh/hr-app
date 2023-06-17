package com.example.hrApplication.payloads.response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.hrApplication.models.department.Department;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.role.Role;

public class EmployeeResponse {

    public EmployeeResponse(Long id, String username, String name, String address, String email, String password,
            Department department, Set<Role> roles, List<LeaveResponse> leaves,
            List<ExpenseClaimResponse> expenseClaims) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.department = department;
        this.roles = roles;
        this.leaves = leaves;
        this.expenseClaims = expenseClaims;
    }

    private Long id;

    private String username;

    private String name;

    private String address;

    private String email;

    private String password;

    private Department department;

    private Set<Role> roles = new HashSet<>();

    private List<LeaveResponse> leaves;

    private List<ExpenseClaimResponse> expenseClaims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<LeaveResponse> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveResponse> leaves) {
        this.leaves = leaves;
    }

    public List<ExpenseClaimResponse> getExpenseClaims() {
        return expenseClaims;
    }

    public void setExpenseClaims(List<ExpenseClaimResponse> expenseClaims) {
        this.expenseClaims = expenseClaims;
    }

}
