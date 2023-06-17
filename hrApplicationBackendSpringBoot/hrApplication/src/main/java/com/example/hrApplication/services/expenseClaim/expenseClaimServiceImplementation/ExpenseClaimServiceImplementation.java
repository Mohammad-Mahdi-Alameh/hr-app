package com.example.hrApplication.services.expenseClaim.expenseClaimServiceImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.ExpenseClaimDetailRequest;
import com.example.hrApplication.payloads.request.ExpenseClaimRequest;
import com.example.hrApplication.payloads.response.ExpenseClaimDetailResponse;
import com.example.hrApplication.payloads.response.ExpenseClaimResponse;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.expenseClaim.ExpenseClaimRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.services.expenseClaim.ExpenseClaimService;
import com.example.hrApplication.services.expenseClaim.expenseClaimDetail.ExpenseClaimDetailService;
import com.example.hrApplication.utils.Utils;

@Service
public class ExpenseClaimServiceImplementation implements ExpenseClaimService {
    @Autowired
    ExpenseClaimRepository expenseClaimRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ExpenseClaimDetailService expenseClaimDetailService;
    @Autowired
    Utils utils;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ExpenseClaimResponse addExpenseClaim(String token, ExpenseClaimRequest expenseClaimRequest) {
        ExpenseClaim expenseClaim = new ExpenseClaim();
        Long employeeId = utils.getUserIdFromJwtToken(jwtUtils, employeeService, token);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        expenseClaim.setDate(expenseClaimRequest.getDate());
        expenseClaim.setDescription(expenseClaimRequest.getDescription());
        expenseClaim.setEmployee(employee);
        expenseClaim.setStatus(expenseClaimRequest.getStatus());
        expenseClaim.setTotal(expenseClaimRequest.getTotal());
        expenseClaim = expenseClaimRepository.save(expenseClaim);
        List<ExpenseClaimDetailRequest> expenseClaimDetailRequests = expenseClaimRequest
                .getExpenseClaimDetailRequests();
        List<ExpenseClaimDetail> expenseClaimDetails = new ArrayList<ExpenseClaimDetail>();
        for (ExpenseClaimDetailRequest expenseClaimDetailRequest : expenseClaimDetailRequests) {
            expenseClaimDetailRequest.setExpenseClaimId(expenseClaim.getId());
            expenseClaimDetails.add(expenseClaimDetailService.addExpenseClaimDetail(expenseClaimDetailRequest));
        }
        expenseClaim.setExpenseClaimDetails(expenseClaimDetails);
        expenseClaimDetails = expenseClaim.getExpenseClaimDetails();
        List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new ArrayList<ExpenseClaimDetailResponse>();
        expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaimDetails);
        return new ExpenseClaimResponse(expenseClaim.getId(), expenseClaim.getEmployee().getId(),
                expenseClaim.getEmployee().getUsername(), expenseClaim.getDate(), expenseClaim.getTotal(),
                expenseClaim.getDescription(), expenseClaim.getStatus(), expenseClaimDetailResponses);
    }
    // public ExpenseClaimDetail addExpenseClaimDetail(String token,
    // ExpenseClaimDetailRequest expenseClaimDetailRequest) {
    // ExpenseClaimDetail expenseClaimDetail = new ExpenseClaimDetail();
    // ExpenseClaimType expenseClaimType =
    // expenseClaimTypeService.getExpenseClaimTypeById(expenseClaimRequest.getExpenseClaimTypeId());
    // expenseClaim.setFromDate(expenseClaimRequest.getFrom());
    // expenseClaim.setToDate(expenseClaimRequest.getTo());
    // expenseClaim.setEmployee(employee);
    // expenseClaim.setExpenseClaimType(expenseClaimType);
    // expenseClaim.setNote(expenseClaimRequest.getNote());
    // expenseClaim.setNumberbOfDays(expenseClaimRequest.getNumberbOfDays());
    // expenseClaimRepository.save(expenseClaim);
    // return expenseClaimDetail;
    // }
    // public ExpenseClaimDetail createExpenseClaimDetail(String token,
    // ExpenseClaimDetailRequest expenseClaimDetailRequest) {
    // ExpenseClaimDetail expenseClaimDetail = new ExpenseClaimDetail();
    // ExpenseClaimType expenseClaimType =
    // expenseClaimTypeService.getExpenseClaimTypeById(expenseClaimRequest.getExpenseClaimTypeId());
    // expenseClaim.setFromDate(expenseClaimRequest.getFrom());
    // expenseClaim.setToDate(expenseClaimRequest.getTo());
    // expenseClaim.setEmployee(employee);
    // expenseClaim.setExpenseClaimType(expenseClaimType);
    // expenseClaim.setNote(expenseClaimRequest.getNote());
    // expenseClaim.setNumberbOfDays(expenseClaimRequest.getNumberbOfDays());
    // expenseClaimRepository.save(expenseClaim);
    // return expenseClaimDetail;
    // }

    @Override
    public List<ExpenseClaimResponse> getAllExpenseClaims() {
        List<ExpenseClaim> expenseClaims = expenseClaimRepository.findAll();
        List<ExpenseClaimResponse> expenseClaimResponses = new ArrayList<ExpenseClaimResponse>();
        for (ExpenseClaim expenseClaim : expenseClaims) {
            List<ExpenseClaimDetail> expenseClaimDetails = expenseClaim.getExpenseClaimDetails();
            List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new ArrayList<ExpenseClaimDetailResponse>();
            expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaimDetails);
            ExpenseClaimResponse expenseClaimResponse = new ExpenseClaimResponse(expenseClaim.getId(),
                    expenseClaim.getEmployee().getId(), expenseClaim.getEmployee().getUsername(),
                    expenseClaim.getDate(),
                    expenseClaim.getTotal(), expenseClaim.getDescription(), expenseClaim.getStatus(),
                    expenseClaimDetailResponses);
            expenseClaimResponses.add(expenseClaimResponse);
        }
        return expenseClaimResponses;
    }

    @Override
    public ExpenseClaimResponse getExpenseClaimById(Long id) {

        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaim", "Id", id));
        List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new ArrayList<ExpenseClaimDetailResponse>();
        expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaim.getExpenseClaimDetails());
        return new ExpenseClaimResponse(expenseClaim.getId(), expenseClaim.getEmployee().getId(),
                expenseClaim.getEmployee().getUsername(), expenseClaim.getDate(), expenseClaim.getTotal(),
                expenseClaim.getDescription(), expenseClaim.getStatus(), expenseClaimDetailResponses);
    }
    // @Override
    // public ExpenseClaimResponse getExpenseClaimById(Long id) {

    // ExpenseClaim expenseClaim = expenseClaimRepository.findById(id)
    // .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaim", "Id", id));
    // List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new
    // ArrayList<ExpenseClaimDetailResponse>();
    // for (ExpenseClaimDetail expenseClaimDetail :
    // expenseClaim.getExpenseClaimDetails()) {
    // ExpenseClaimDetailResponse expenseClaimDetailResponse = new
    // ExpenseClaimDetailResponse(
    // expenseClaimDetail.getId(), expenseClaimDetail.getExpenseClaim().getId(),
    // expenseClaimDetail.getExpenseClaimType().getId(),
    // expenseClaimDetail.getExpenseClaimType().getName(),
    // expenseClaimDetail.getDate(),
    // expenseClaimDetail.getTotal(), expenseClaimDetail.getDescription());
    // expenseClaimDetailResponses.add(expenseClaimDetailResponse);
    // }
    // return new ExpenseClaimResponse(expenseClaim.getId(),
    // expenseClaim.getEmployee().getId(),
    // expenseClaim.getEmployee().getUsername(), expenseClaim.getDate(),
    // expenseClaim.getTotal(),
    // expenseClaim.getDescription(), expenseClaim.getStatus(),
    // expenseClaimDetailResponses);
    // }

    @Override
    public ExpenseClaimResponse updateExpenseClaim(ExpenseClaimRequest expenseClaimRequest, Long id) {
        // we need to check whether expenseClaim with given id is exist in DB or not
        ExpenseClaim expenseClaim = expenseClaimRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ExpenseClaim", "Id", id));
        List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new ArrayList<ExpenseClaimDetailResponse>();
        expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaim.getExpenseClaimDetails());
        String expenseClaimDate = expenseClaimRequest.getDate();
        String expenseClaimDescription = expenseClaimRequest.getDescription();
        String expenseClaimStatus = expenseClaimRequest.getStatus();
        int expenseClaimTotal = expenseClaimRequest.getTotal();
        if (expenseClaimDate != null) {
            expenseClaim.setDate(expenseClaimDate);
        }
        if (expenseClaimDescription != null) {
            expenseClaim.setDescription(expenseClaimDescription);
        }
        if (expenseClaimStatus != null) {
            expenseClaim.setStatus(expenseClaimStatus);
        }
        if (expenseClaimTotal != 0) {
            expenseClaim.setTotal(expenseClaimTotal);
        }
        // save existing expenseClaim to DB
        expenseClaimRepository.save(expenseClaim);
        return new ExpenseClaimResponse(expenseClaim.getId(), expenseClaim.getEmployee().getId(),
                expenseClaim.getEmployee().getUsername(), expenseClaim.getDate(), expenseClaim.getTotal(),
                expenseClaim.getDescription(), expenseClaim.getStatus(), expenseClaimDetailResponses);
    }

    @Override
    public MessageResponse deleteExpenseClaim(Long id) {
        expenseClaimRepository.deleteById(id);
        return new MessageResponse("ExpenseClaim deleted successfully !");
    }

}
