package com.example.hrApplication.security.services.userService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.user.User;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Employee getEmployeeById(Long Id);
}