package com.example.hrApplication.services.expenseClaim;

import java.util.List;

import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.payloads.request.ExpenseClaimRequest;
import com.example.hrApplication.payloads.response.ExpenseClaimResponse;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface ExpenseClaimService {
	ExpenseClaimResponse addExpenseClaim(String token , ExpenseClaimRequest expenseClaim);

	List<ExpenseClaimResponse> getAllExpenseClaims();

	ExpenseClaimResponse getExpenseClaimById(Long id);

	ExpenseClaimResponse updateExpenseClaim(ExpenseClaimRequest expenseClaim,Long id);

	MessageResponse deleteExpenseClaim(Long id);

	// Employee updateEmployee(EmployeeRequest employee, Long id);

	// MessageResponse deleteEmployee(Long id);

	// UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    // User getUserById(Long Id);
}
