package com.example.hrApplication.repositories.expenseClaim.expenseClaimDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.expenseClaim.ExpenseClaimDetail;
import com.example.hrApplication.models.user.User;

@Repository
public interface ExpenseClaimDetailRepository extends JpaRepository<ExpenseClaimDetail, Long> {
//     Optional<User> findByUsername(String username);

//     Boolean existsByUsername(String username);

//     Boolean existsByEmail(String email);
}