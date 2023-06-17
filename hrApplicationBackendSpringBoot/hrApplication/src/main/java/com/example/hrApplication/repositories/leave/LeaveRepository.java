package com.example.hrApplication.repositories.leave;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.user.User;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    // Optional<List<Leave>> findByFrom(String username);
    // Optional<List<Leave>> findByTo(String username);
    Optional<List<Leave>> findByEmployee(String username);
    
    // Optional<User> findByUsername(String username);

    // Boolean existsByUsername(String username);

    // Boolean existsByEmail(String email);
}


