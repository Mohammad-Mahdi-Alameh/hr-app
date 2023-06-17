package com.example.hrApplication.repositories.expenseClaim.expenseClaimType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hrApplication.models.expenseClaim.ExpenseClaim;
import com.example.hrApplication.models.expenseClaim.ExpenseClaimType;
import com.example.hrApplication.models.user.User;

@Repository
public interface ExpenseClaimTypeRepository extends JpaRepository<ExpenseClaimType, Long> {
    Optional<ExpenseClaimType> findByName(String name);
}