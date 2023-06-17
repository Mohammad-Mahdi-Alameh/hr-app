package com.example.hrApplication.controllers.v1.admin.leave;


import java.util.List;

import javax.validation.Valid;

import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.user.User;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.security.services.userService.UserService;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.services.leave.LeaveService;
import com.example.hrApplication.utils.Utils;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/v1/admin/leaves")
@PreAuthorize("hasRole('ADMIN')")
public class AdminLeaveController {
    
	@Autowired
	private JwtUtils jwtUtils;
    @Autowired
    private Utils utils;
	@Autowired
    private EmployeeService employeeService;
    @Autowired
    private LeaveService leaveService;

    public AdminLeaveController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<LeaveResponse>> getAllLeaves() {
        return new ResponseEntity<List<LeaveResponse>>(leaveService.getAllLeaves(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<LeaveResponse> getLeaveById(@PathVariable("id") long leaveId) {
        return new ResponseEntity<LeaveResponse>(leaveService.getLeaveById(leaveId), HttpStatus.OK);
    }
}