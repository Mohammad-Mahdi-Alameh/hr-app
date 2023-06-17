package com.example.hrApplication.services.expenseClaim.expenseClaimDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.ExpenseClaimDetailRequest;
import com.example.hrApplication.payloads.response.ExpenseClaimDetailResponse;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface ExpenseClaimDetailService {

    ExpenseClaimDetail addExpenseClaimDetail(ExpenseClaimDetailRequest expenseClaimDetailRequest);

	List<ExpenseClaimDetailResponse> getAllExpenseClaimDetails();

	ExpenseClaimDetail getExpenseClaimDetailById(Long id);

	ExpenseClaimDetail updateExpenseClaimDetail(ExpenseClaimDetailRequest expenseClaimDetailRequest, Long id);

	MessageResponse deleteExpenseClaimDetail(Long id);

}
