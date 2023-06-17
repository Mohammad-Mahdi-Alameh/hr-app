package com.example.hrApplication.repositories.department;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.department.Department;
import java.util.List;
import java.util.Optional;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Optional<Department> findByName(String name);

}