package com.example.hrApplication.services.employee;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.response.EmployeeResponse;
import com.example.hrApplication.payloads.response.ExpenseClaimResponse;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface EmployeeService {
	Employee addEmployee(EmployeeRequest employee);

	List<Employee> getAllEmployees();

	EmployeeResponse getEmployeeById(Long id);

	Employee updateEmployee(EmployeeRequest employee, Long id);

	List<LeaveResponse> getEmployeeLeaves(String token);

	List<ExpenseClaimResponse> getEmployeeExpenseClaims(String token);

	MessageResponse deleteEmployee(Long id);

	Long loadEmployeeIdByUsername(String username);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	// User getUserById(Long Id);
}
