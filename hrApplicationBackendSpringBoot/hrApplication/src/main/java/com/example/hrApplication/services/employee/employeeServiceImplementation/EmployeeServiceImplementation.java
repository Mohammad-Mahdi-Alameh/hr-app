package com.example.hrApplication.services.employee.employeeServiceImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hrApplication.exceptions.ResourceNotFoundException;
import com.example.hrApplication.models.department.Department;
// import com.example.hrApplication.exceptions.UnsupportedEmployeeCreationException;
import com.example.hrApplication.models.employee.Employee;
import com.example.hrApplication.models.erole.ERole;
import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.leave.Leave;
import com.example.hrApplication.models.role.Role;
import com.example.hrApplication.models.user.User;
import com.example.hrApplication.payloads.request.EmployeeRequest;
import com.example.hrApplication.payloads.response.EmployeeResponse;
import com.example.hrApplication.payloads.response.ExpenseClaimDetailResponse;
import com.example.hrApplication.payloads.response.ExpenseClaimResponse;
import com.example.hrApplication.payloads.response.LeaveResponse;
import com.example.hrApplication.payloads.response.MessageResponse;
import com.example.hrApplication.repositories.department.DepartmentRepository;
import com.example.hrApplication.repositories.employee.EmployeeRepository;
import com.example.hrApplication.repositories.role.RoleRepository;
import com.example.hrApplication.repositories.user.UserRepository;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.security.services.userDetailsImplementation.UserDetailsImpl;
import com.example.hrApplication.services.employee.EmployeeService;
import com.example.hrApplication.utils.Utils;

@Service
public class EmployeeServiceImplementation implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private Utils utils;

    @Override
    public Employee addEmployee(EmployeeRequest employee) {
        String username = employee.getUsername();
        String email = employee.getEmail();
        String password = encoder.encode(employee.getPassword());
        if (employeeRepository.existsByUsername(username)) {
            return null;
        }

        if (employeeRepository.existsByEmail(email)) {
            return null;
        }
        Set<Role> roles = new HashSet<>();
        Role employeeRole = new Role();
        employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Name", "UserRole"));
        roles.add(employeeRole);
        // User user = new User();
        // user.setEmail(email);
        // user.setUsername(username);
        // user.setPassword(password);
        // user.setRoles(roles);
        // userRepository.save(user);
        Department department = new Department();
        String employeeDepartmentName = employee.getDepartmentName();
        if (employeeDepartmentName != null) {
            department = departmentRepository.findByName(employeeDepartmentName)
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "Name", employeeDepartmentName));
        }

        Employee newEmployee = new Employee();
        newEmployee.setName(employee.getName());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setEmail(email);
        newEmployee.setUsername(username);
        newEmployee.setPassword(password);
        newEmployee.setDepartment(department);
        newEmployee.setRoles(roles);
        employeeRepository.save(newEmployee);
        return newEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return employeeRepository.findAll(sort);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        List<ExpenseClaimResponse> expenseClaimResponses = extractExpenseClaimResponses(employee.getExpenseClaims());
        List<LeaveResponse> leaveResponses = extractLeaveResponses(employee.getLeaves());
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),employee.getUsername(),employee.getName(),employee.getAddress(),employee.getEmail(),employee.getPassword(),employee.getDepartment(),employee.getRoles(),leaveResponses,expenseClaimResponses);
        return employeeResponse;
    }

    private List<ExpenseClaimResponse> extractExpenseClaimResponses(
            List<ExpenseClaim> expenseClaims) {
        List<ExpenseClaimResponse> expenseClaimResponses = new ArrayList<ExpenseClaimResponse>();
        for (ExpenseClaim expenseClaim : expenseClaims) {
            List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = utils.extractExpenseClaimDetailResponses(expenseClaim.getExpenseClaimDetails());
            ExpenseClaimResponse expenseClaimResponse = new ExpenseClaimResponse(expenseClaim.getId(),expenseClaim.getEmployee().getId(),expenseClaim.getEmployee().getUsername(),expenseClaim.getDate(),expenseClaim.getTotal(),expenseClaim.getDescription(),expenseClaim.getStatus(),expenseClaimDetailResponses);
            expenseClaimResponses.add(expenseClaimResponse);
        }
        return expenseClaimResponses;
    }

    private List<LeaveResponse> extractLeaveResponses(List<Leave> leaves) {
        List<LeaveResponse> leaveResponses = new ArrayList<LeaveResponse>();
        for (Leave leave : leaves) {
            LeaveResponse leaveResponse = new LeaveResponse(leave.getId(), leave.getFromDate(), leave.getToDate(),
                    leave.getNumberbOfDays(), leave.getNote(), leave.getLeaveType().getName(),
                    leave.getEmployee().getId(), leave.getEmployee().getUsername());
            leaveResponses.add(leaveResponse);
        }
        return leaveResponses;
    }

    @Override
    public Employee updateEmployee(EmployeeRequest employeeRequest, Long id) {
        // we need to check whether employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));
        String employeeName = employeeRequest.getName();
        String employeeEmail = employeeRequest.getEmail();
        String employeePassword = employeeRequest.getPassword();
        String employeeAddress = employeeRequest.getAddress();
        String employeeDepartmentName = employeeRequest.getDepartmentName();
        if (employeeName != null) {
            existingEmployee.setName(employeeName);
        }
        if (employeeEmail != null) {
            existingEmployee.setEmail(employeeEmail);
        }
        if (employeePassword != null) {
            existingEmployee.setPassword(employeePassword);
        }
        if (employeeAddress != null) {
            existingEmployee.setAddress(employeeAddress);
        }
        if (employeeDepartmentName != null) {
            existingEmployee.setDepartment(departmentRepository.findByName(employeeDepartmentName)
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "Name", employeeDepartmentName)));
        }
        // save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public MessageResponse deleteEmployee(Long id) {
        // check whether a employee exist in a DB or not
        // employeeRepository.findById(id).orElseThrow(() -> new
        // ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
        return new MessageResponse("Employee deleted successfully !");
    }

    // @Autowired
    // UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(employee);
    }

    @Override
    public Long loadEmployeeIdByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return employee.getId();

    }

    @Override
    public List<LeaveResponse> getEmployeeLeaves(String token) {
        Long employeeId = utils.getUserIdFromJwtToken(jwtUtils, this, token);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        List<Leave> leaves = employee.getLeaves();
        List<LeaveResponse> leaveResponses = new ArrayList<LeaveResponse>();
        for (Leave leave : leaves) {
            LeaveResponse leaveResponse = new LeaveResponse(leave.getId(), leave.getFromDate(), leave.getToDate(),
                    leave.getNumberbOfDays(), leave.getNote(), leave.getLeaveType().getName(),
                    leave.getEmployee().getId(), leave.getEmployee().getUsername());
            leaveResponses.add(leaveResponse);
        }
        return leaveResponses;
    }

    @Override
    public List<ExpenseClaimResponse> getEmployeeExpenseClaims(String token) {
        Long employeeId = utils.getUserIdFromJwtToken(jwtUtils, this, token);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", employeeId));
        return extractExpenseClaimResponses(employee.getExpenseClaims());
    }
}

// public Long getUserIdFromJwtToken(JwtUtils jwtUtils,EmployeeService
// employeeService,String token) {
// // UserDetailsImpl userDetails =
// (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

// String username = jwtUtils.getUserNameFromJwtToken(token);

// Long employeeId = employeeService.loadEmployeeIdByUsername(username);
// return employeeId;

// // return
// Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
// }

// @Override
// public User getUserById(Long id) {
// User user = userRepository.findById(id)
// .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " +
// id));
// return user;
// }
