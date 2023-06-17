package com.example.hrApplication.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.payloads.response.ExpenseClaimDetailResponse;
import com.example.hrApplication.security.jwt.JwtUtils;
import com.example.hrApplication.services.employee.EmployeeService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class Utils {
    public Long getUserIdFromJwtToken(JwtUtils jwtUtils, EmployeeService employeeService, String token) {
        // UserDetailsImpl userDetails =
        // (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = jwtUtils.getUserNameFromJwtToken(token);

        Long employeeId = employeeService.loadEmployeeIdByUsername(username);
        return employeeId;

        // return
        // Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
      public List<ExpenseClaimDetailResponse> extractExpenseClaimDetailResponses(
            List<ExpenseClaimDetail> expenseClaimDetails) {
        List<ExpenseClaimDetailResponse> expenseClaimDetailResponses = new ArrayList<ExpenseClaimDetailResponse>();
        for (ExpenseClaimDetail expenseClaimDetail : expenseClaimDetails) {
            ExpenseClaimDetailResponse expenseClaimDetailResponse = new ExpenseClaimDetailResponse(
                    expenseClaimDetail.getId(), expenseClaimDetail.getExpenseClaim().getId(),
                    expenseClaimDetail.getExpenseClaimType().getId(),
                    expenseClaimDetail.getExpenseClaimType().getName(), expenseClaimDetail.getDate(),
                    expenseClaimDetail.getTotal(), expenseClaimDetail.getDescription());
            expenseClaimDetailResponses.add(expenseClaimDetailResponse);
        }
        return expenseClaimDetailResponses;
    }
}

// private static String secretKey = "your-secret-key"; // Replace with your
// secret key
// public static Claims parseToken(String token) {
// String[] chunks = token.split("\\.");
// Base64.Decoder decoder = Base64.getUrlDecoder();

// String header = new String(decoder.decode(chunks[0]));
// String payload = new String(decoder.decode(chunks[1]));
// return
// Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
// }
