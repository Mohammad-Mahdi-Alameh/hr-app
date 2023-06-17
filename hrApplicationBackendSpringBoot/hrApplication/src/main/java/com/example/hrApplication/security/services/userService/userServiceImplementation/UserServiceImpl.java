package com.example.hrApplication.security.services.userService.userServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hrApplication.models.employee.Employee;
// import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.user.User;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.services.userDetailsImplementation.UserDetailsImpl;
import com.example.hrApplication.security.services.userService.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(employee);
	}

	public Long loadUserIdByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return employee.getId();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
		return employee;
	}

	// @Override
	// public User getUserById(Long userId){
	// return userRepository.findById(userId).orElseThrow(() ->
	// new ResourceNotFoundException("User", "Id", userId));

	// }

}