package com.example.hrApplication.controllers.v1.admin.leave.leaveType;


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
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.models.user.User;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.LeaveTypeRequest;
import com.example.hrApplication.payloads.response.LeaveTypeResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.security.services.userService.UserService;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.services.leave.LeaveService;
import com.example.hrApplication.services.leave.leaveType.LeaveTypeService;
import com.example.hrApplication.utils.Utils;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/v1/admin/leave_types")
@PreAuthorize("hasRole('ADMIN')")
public class AdminLeaveTypeController {
    
	@Autowired
	private JwtUtils jwtUtils;
    @Autowired
    private Utils utils;
	@Autowired
    private EmployeeService employeeService;
    @Autowired
    private LeaveTypeService leaveTypeService;

    public AdminLeaveTypeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

   @PostMapping("")
    public ResponseEntity<LeaveType> addLeaveType( @Valid @RequestBody LeaveTypeRequest leaveTypeRequest) {
         return new ResponseEntity<LeaveType>(leaveTypeService.addLeaveType(leaveTypeRequest.getLeaveTypeName()), HttpStatus.OK);
    }

    // build get all employees RESTful API
    @GetMapping
    public List<LeaveTypeResponse> getAlleaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    // build get employee by id RESTful API
    @GetMapping("{id}")
    public ResponseEntity<LeaveType> getLeaveTypeById(@PathVariable("id") long leaveTypeId) {
        return new ResponseEntity<LeaveType>(leaveTypeService.getLeaveTypeById(leaveTypeId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<LeaveType> updateLeaveType(@RequestBody LeaveTypeRequest leaveTypeRequest , @PathVariable("id") long leaveTypeId) {
        return new ResponseEntity<LeaveType>(leaveTypeService.updateLeaveType(leaveTypeRequest.getLeaveTypeName(),leaveTypeId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteEmployeeById(@PathVariable("id") long leaveTypeId) {
        return new ResponseEntity<MessageResponse>(leaveTypeService.deleteLeaveType(leaveTypeId), HttpStatus.OK);
    }
   
}