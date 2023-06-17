package com.example.hrApplication.services.expenseClaim.expenseClaimType;

import java.util.List;
import java.util.Optional;

import com.example.hrApplication.models.expenseClaim.ExpenseClaimType;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface ExpenseClaimTypeService {

    List<ExpenseClaimType> getAllExpenseClaimTypes();

    ExpenseClaimType getExpenseClaimTypeById(Long id);

    ExpenseClaimType getExpenseClaimTypeByName(String name);

}
