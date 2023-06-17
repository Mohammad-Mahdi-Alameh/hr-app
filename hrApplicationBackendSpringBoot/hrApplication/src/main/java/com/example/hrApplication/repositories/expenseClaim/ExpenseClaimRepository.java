package com.example.hrApplication.repositories.expenseClaim;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.user.User;

@Repository
public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Long> {
    // Optional<User> findByUsername(String username);

    // Boolean existsByUsername(String username);

    // Boolean existsByEmail(String email);
}