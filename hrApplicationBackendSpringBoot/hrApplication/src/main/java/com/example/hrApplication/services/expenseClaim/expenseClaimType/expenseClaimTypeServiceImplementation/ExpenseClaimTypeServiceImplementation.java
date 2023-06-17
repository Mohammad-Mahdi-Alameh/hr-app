package com.example.hrApplication.services.expenseClaim.expenseClaimType.expenseClaimTypeServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimType;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.request.ExpenseClaimDetailRequest;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.expenseClaim.expenseClaimType.ExpenseClaimTypeRepository;
import com.example.hrApplication.repositories.leave.leaveType.LeaveTypeRepository;
import com.example.hrApplication.services.expenseClaim.expenseClaimDetail.ExpenseClaimDetailService;
import com.example.hrApplication.services.expenseClaim.expenseClaimType.ExpenseClaimTypeService;
import com.example.hrApplication.services.leave.leaveType.LeaveTypeService;

@Service
public class ExpenseClaimTypeServiceImplementation implements ExpenseClaimTypeService {

  @Autowired
  ExpenseClaimTypeRepository expenseClaimTypeRepository;

  @Override
  public List<ExpenseClaimType> getAllExpenseClaimTypes() {
    return expenseClaimTypeRepository.findAll();
  }

  @Override
  public ExpenseClaimType getExpenseClaimTypeById(Long id) {
    return expenseClaimTypeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaimDetailType", "Id", id));
  }

  @Override
  public ExpenseClaimType getExpenseClaimTypeByName(String name) {
    return expenseClaimTypeRepository.findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("ExpenseClaimDetailType", "Name", name));
  }

}
