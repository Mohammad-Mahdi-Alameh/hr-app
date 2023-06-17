package com.example.hrApplication.repositories.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.user.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Optional<Employee> findByUsername(String username);

    // Boolean existsByUsername(String username);
     Optional<Employee> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    // Optional<List<Employee>> findByFrom(Long id);

    // Optional<List<Employee>> findByTo(Long id);
}
