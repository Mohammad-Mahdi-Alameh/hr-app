package com.example.hrApplication.controllers.v1.admin.employee;

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
import com.example.hrApplication.payloads.response.EmployeeResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.security.services.userService.UserService;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.utils.Utils;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/v1/admin/employees")
@PreAuthorize("hasRole('ADMIN')")
public class AdminEmployeeController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private Utils utils;

    public AdminEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<Employee>(employeeService.addEmployee(employeeRequest), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
            @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employeeRequest, employeeId), HttpStatus.OK);
    }

    // build get all employees RESTful API
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // build get employee by id RESTful API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<EmployeeResponse>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<MessageResponse>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }

    // @GetMapping("/protected")
    // public String protectedEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
    //     String token = authorizationHeader.replace("Bearer ", "");
    //     Long userId = utils.getUserIdFromJwtToken(jwtUtils, employeeService, token);
    //     Employee employee = employeeService.getEmployeeById(userId);
    //     // String username = claims.getSubject();

    //     return "Protected endpoint accessed by " + employee.getUsername();
    // }
}
