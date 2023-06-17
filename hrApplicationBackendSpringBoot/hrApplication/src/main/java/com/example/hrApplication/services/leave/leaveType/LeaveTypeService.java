package com.example.hrApplication.services.leave.leaveType;

import java.util.List;
import java.util.Optional;

import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.response.LeaveTypeResponse;
import com.example.hrApplication.payloads.response.MessageResponse;

public interface LeaveTypeService {

    LeaveType addLeaveType(String leaveTypeName);

    LeaveType updateLeaveType(String leaveTypeName, Long id);

    MessageResponse deleteLeaveType(Long id);

    List<LeaveTypeResponse> getAllLeaveTypes();

    LeaveType getLeaveTypeById(Long id);
    
    LeaveType getLeaveTypeByName(String name);
}
