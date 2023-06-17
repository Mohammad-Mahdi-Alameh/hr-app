package com.example.hrApplication.controllers.v1.employee.expenseClaim.expenseClaimDetail;

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
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.user.User;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.ExpenseClaimDetailRequest;
import com.example.hrApplication.payloads.request.LeaveRequest;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.security.services.userService.UserService;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.services.expenseClaim.expenseClaimDetail.ExpenseClaimDetailService;
import com.example.hrApplication.services.leave.LeaveService;

import io.jsonwebtoken.Claims;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/v1/employee/expense_claim_details")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeExpenseClaimDetailController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ExpenseClaimDetailService expenseClaimDetailService;

    public EmployeeExpenseClaimDetailController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("")
    public ResponseEntity<ExpenseClaimDetail> addExpenseClaimDetail(@RequestHeader("Authorization") String authorizationHeader,
             @Valid @RequestBody ExpenseClaimDetailRequest expenseClaimDetailRequest) {
        return new ResponseEntity<ExpenseClaimDetail>(
                expenseClaimDetailService.addExpenseClaimDetail(expenseClaimDetailRequest), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ExpenseClaimDetail> updateExpenseClaimDetail(@Valid @RequestBody ExpenseClaimDetailRequest expenseClaimDetailRequest,
            @PathVariable("id") long expenseClaimDetailId) {
        return new ResponseEntity<ExpenseClaimDetail>(
                expenseClaimDetailService.updateExpenseClaimDetail(expenseClaimDetailRequest, expenseClaimDetailId), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<ExpenseClaimDetail> getExpenseClaimDetail(@PathVariable("id") long expenseClaimDetailId) {
        return new ResponseEntity<ExpenseClaimDetail>(
                expenseClaimDetailService.getExpenseClaimDetailById(expenseClaimDetailId), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteExpenseClaimDetail(@PathVariable("id") long expenseClaimDetailId) {
        return new ResponseEntity<MessageResponse>(
                expenseClaimDetailService.deleteExpenseClaimDetail(expenseClaimDetailId), HttpStatus.OK);
    }

}