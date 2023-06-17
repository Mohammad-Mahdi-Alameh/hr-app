package com.example.hrApplication.services.leave.leaveServiceImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.request.LeaveRequest;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.leave.LeaveRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.services.leave.LeaveService;
import com.example.hrApplication.services.leave.leaveType.LeaveTypeService;
import com.example.hrApplication.utils.Utils;

@Service
public class LeaveServiceImplementation implements LeaveService {
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveTypeService leaveTypeService;
    @Autowired
    Utils utils;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public LeaveResponse addLeave(String token, LeaveRequest leaveRequest) {
        Leave leave = new Leave();
        Long employeeId = utils.getUserIdFromJwtToken(jwtUtils, employeeService, token);
        Employee employee = employeeRepository.findById(employeeId).  orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        LeaveType leaveType = leaveTypeService.getLeaveTypeByName(leaveRequest.getLeaveType());
        String from = leaveRequest.getFromDate();
        String to = leaveRequest.getToDate();
        String note = leaveRequest.getNote();
        int numberOfDays = leaveRequest.getNumberOfDays();
        String leaveTypeName = leaveType.getName();

        leave.setFromDate(from);
        leave.setToDate(to);
        leave.setEmployee(employee);
        leave.setLeaveType(leaveType);
        leave.setNote(note);
        leave.setNumberbOfDays(numberOfDays);
        Leave savedLeave = leaveRepository.save(leave);
        return new LeaveResponse(savedLeave.getId(),from, to, numberOfDays, note, leaveTypeName,employee.getId(),employee.getUsername());
    }

    @Override
    public List<LeaveResponse> getAllLeaves() {
        List<Leave> leaves = leaveRepository.findAll();
        List<LeaveResponse> leaveResponses = new ArrayList<LeaveResponse>();
        for (Leave leave : leaves) {
            LeaveResponse leaveResponse = new LeaveResponse(leave.getId(),leave.getFromDate(), leave.getToDate(),
                    leave.getNumberbOfDays(), leave.getNote(), leave.getLeaveType().getName(),leave.getEmployee().getId(),leave.getEmployee().getUsername());
            leaveResponses.add(leaveResponse);
        }
        return leaveResponses;
    }

    @Override
    public LeaveResponse getLeaveById(Long id) {
        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave", "Id", id));
        return new LeaveResponse(leave.getId(),leave.getFromDate(), leave.getToDate(),
                    leave.getNumberbOfDays(), leave.getNote(), leave.getLeaveType().getName(),leave.getEmployee().getId(),leave.getEmployee().getUsername());
    }

    @Override
    public LeaveResponse updateLeave(LeaveRequest leaveRequest, Long id) {
        // we need to check whether leave with given id is exist in DB or not
        Leave existingLeave = leaveRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Leave", "Id", id));
        String leaveFrom = leaveRequest.getFromDate();
        String leaveTo = leaveRequest.getToDate();
        String leavNote = leaveRequest.getNote();
        int leaveNbOfDays = leaveRequest.getNumberOfDays();
        String leaveTypeName = leaveRequest.getLeaveType();
        if (leaveFrom != null) {
            existingLeave.setFromDate(leaveFrom);
        }
        if (leaveTo != null) {
            existingLeave.setToDate(leaveTo);
        }
        if (leavNote != null) {
            existingLeave.setNote(leavNote);
        }
        if (leaveNbOfDays != 0) {
            existingLeave.setNumberbOfDays(leaveNbOfDays);
        }
        if (leaveTypeName != null) {
            existingLeave.setLeaveType(leaveTypeService.getLeaveTypeByName(leaveTypeName));
        }
        // save existing leave to DB
        leaveRepository.save(existingLeave);
        return new LeaveResponse(existingLeave.getId(),existingLeave.getFromDate(), existingLeave.getToDate(),
                    existingLeave.getNumberbOfDays(), existingLeave.getNote(), existingLeave.getLeaveType().getName(),existingLeave.getEmployee().getId(),existingLeave.getEmployee().getUsername());
    }

    @Override
    public MessageResponse deleteLeave(Long id) {
        leaveRepository.deleteById(id);
        return new MessageResponse("Leave deleted successfully !");
    }

}
