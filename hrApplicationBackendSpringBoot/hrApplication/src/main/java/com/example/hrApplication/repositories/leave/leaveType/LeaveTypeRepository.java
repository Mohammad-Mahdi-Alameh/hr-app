package com.example.hrApplication.repositories.leave.leaveType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.leave.LeaveType;
import com.example.hrApplication.models.user.User;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
  Optional<LeaveType> findByName(String name);
   
}

