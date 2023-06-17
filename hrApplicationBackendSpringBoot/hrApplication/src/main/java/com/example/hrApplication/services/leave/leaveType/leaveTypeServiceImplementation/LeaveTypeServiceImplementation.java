package com.example.hrApplication.services.leave.leaveType.leaveTypeServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.LeaveTypeResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.leave.leaveType.LeaveTypeRepository;
import com.example.hrApplication.services.leave.leaveType.LeaveTypeService;

@Service
public class LeaveTypeServiceImplementation implements LeaveTypeService {

  @Autowired
  LeaveTypeRepository leaveTypeRepository;

  @Override
  public LeaveType getLeaveTypeById(Long id) {
    return leaveTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LeaveType", "Id", id));
  }

  @Override
  public LeaveType addLeaveType(String leaveTypeName) {
    LeaveType leaveType = new LeaveType();
    leaveType.setName(leaveTypeName);
    leaveTypeRepository.save(leaveType);
    return leaveType;
  }

  @Override
  public LeaveType updateLeaveType(String leaveTypeName, Long id) {
    LeaveType leaveType = getLeaveTypeById(id);
    leaveType.setName(leaveTypeName);
    leaveTypeRepository.save(leaveType);
    return leaveType;
  }

  @Override
  public MessageResponse deleteLeaveType(Long id) {
    leaveTypeRepository.deleteById(id);
    return new MessageResponse("Leave Type deleted successfully !");
  }

  @Override
  public List<LeaveTypeResponse> getAllLeaveTypes() {
    List<LeaveType> leaveTypes = leaveTypeRepository.findAll();
    List<LeaveTypeResponse> leaveTypeResponses = new ArrayList<LeaveTypeResponse>();
    List<Leave> leaves = new ArrayList<Leave>();
    List<LeaveResponse> leaveResponses = new ArrayList<LeaveResponse>();
    for (LeaveType leaveType : leaveTypes) {
      leaves = leaveType.getLeaves();
      for (Leave leave : leaves) {
        LeaveResponse leaveResponse = new LeaveResponse(leave.getId(), leave.getFromDate(), leave.getToDate(),
            leave.getNumberbOfDays(), leave.getNote(), leave.getLeaveType().getName(), leave.getEmployee().getId(),
            leave.getEmployee().getUsername());
        leaveResponses.add(leaveResponse);
      }

      leaveTypeResponses.add(new LeaveTypeResponse(leaveType.getId(), leaveType.getName(),
          leaveResponses));
    }
    return leaveTypeResponses;
  }

  @Override
  public LeaveType getLeaveTypeByName(String name) {
     return leaveTypeRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("LeaveType", "Name", name));
  }

}
