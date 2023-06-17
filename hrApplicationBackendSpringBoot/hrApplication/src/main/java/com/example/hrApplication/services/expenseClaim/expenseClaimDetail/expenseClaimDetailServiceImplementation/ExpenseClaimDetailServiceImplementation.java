package com.example.hrApplication.services.expenseClaim.expenseClaimDetail.expenseClaimDetailServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimType;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.ExpenseClaimDetailRequest;
import com.example.hrApplication.payloads.response.ExpenseClaimDetailResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.expenseClaim.ExpenseClaimRepository;
import com.example.hrApplication.repositories.expenseClaim.expenseClaimDetail.ExpenseClaimDetailRepository;
import com.example.hrApplication.repositories.leave.leaveType.LeaveTypeRepository;
import com.example.hrApplication.services.expenseClaim.ExpenseClaimService;
import com.example.hrApplication.services.expenseClaim.expenseClaimDetail.ExpenseClaimDetailService;
import com.example.hrApplication.services.expenseClaim.expenseClaimType.ExpenseClaimTypeService;
import com.example.hrApplication.services.leave.leaveType.LeaveTypeService;
import com.example.hrApplication.utils.Utils;

@Service
public class ExpenseClaimDetailServiceImplementation implements ExpenseClaimDetailService {

  @Autowired
  ExpenseClaimDetailRepository expenseClaimDetailRepository;
  @Autowired
  Utils utils;
  // @Autowired
  // ExpenseClaimService expenseClaimService;
  @Autowired
  ExpenseClaimRepository expenseClaimRepository;
  @Autowired
  ExpenseClaimTypeService expenseClaimTypeService;

  @Override
  public ExpenseClaimDetail addExpenseClaimDetail(ExpenseClaimDetailRequest expenseClaimDetailRequest) {
    ExpenseClaimDetail expenseClaimDetail = new ExpenseClaimDetail();
    Long expenseClaimId = expenseClaimDetailRequest.getExpenseClaimId();
    ExpenseClaim expenseClaim = expenseClaimRepository.findById(expenseClaimId)
        .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaim", "Id", expenseClaimId));
    ExpenseClaimType expenseClaimDetailType = expenseClaimTypeService
        .getExpenseClaimTypeByName(expenseClaimDetailRequest.getType());
    expenseClaimDetail.setDate(expenseClaimDetailRequest.getDate());
    expenseClaimDetail.setDescription(expenseClaimDetailRequest.getDescription());
    expenseClaimDetail.setExpenseClaimType(expenseClaimDetailType);
    expenseClaimDetail.setTotal(expenseClaimDetailRequest.getTotal());
    expenseClaimDetail.setExpenseClaim(expenseClaim);
    expenseClaimDetailRepository.save(expenseClaimDetail);
    return expenseClaimDetail;
  }

  @Override
  public List<ExpenseClaimDetailResponse> getAllExpenseClaimDetails() {
    List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaimDetailRepository.findAll());
    return expenseClaimDetailResponses;
  }

  @Override
  public ExpenseClaimDetail getExpenseClaimDetailById(Long id) {
    ExpenseClaimDetail expenseClaimDetail = expenseClaimDetailRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaimDetail", "Id", id));
    return expenseClaimDetail;
  }

  @Override
  public ExpenseClaimDetail updateExpenseClaimDetail(ExpenseClaimDetailRequest expenseClaimDetailRequest, Long id) {
    // we need to check whether expenseClaim with given id is exist in DB or not
    ExpenseClaimDetail existingExpenseClaimDetail = expenseClaimDetailRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("ExpenseClaim", "Id", id));
    String expenseClaimDetailDate = expenseClaimDetailRequest.getDate();
    String expenseClaimDetailDescription = expenseClaimDetailRequest.getDescription();
    int expenseClaimDetailTotal = expenseClaimDetailRequest.getTotal();
    if (expenseClaimDetailDate != null) {
      existingExpenseClaimDetail.setDate(expenseClaimDetailDate);
    }
    if (expenseClaimDetailDescription != null) {
      existingExpenseClaimDetail.setDescription(expenseClaimDetailDescription);
    }
    if (expenseClaimDetailTotal != 0) {
      existingExpenseClaimDetail.setTotal(expenseClaimDetailTotal);
    }
    // save existing expenseClaim to DB
    expenseClaimDetailRepository.save(existingExpenseClaimDetail);
    return existingExpenseClaimDetail;
  }

  @Override
  public MessageResponse deleteExpenseClaimDetail(Long id) {
    expenseClaimDetailRepository.deleteById(id);
    return new MessageResponse("ExpenseClaimDetail deleted successfully !");
  }

}
