package com.example.hrApplication.services.leave;

import java.util.List;

import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.payloads.request.LeaveRequest;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface LeaveService {
	LeaveResponse addLeave(String token , LeaveRequest leave);

	List<LeaveResponse> getAllLeaves();

	LeaveResponse getLeaveById(Long id);

	LeaveResponse updateLeave(LeaveRequest leave,Long id);

	MessageResponse deleteLeave(Long id);

	// Employee updateEmployee(EmployeeRequest employee, Long id);

	// MessageResponse deleteEmployee(Long id);

	// UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    // User getUserById(Long Id);
}
