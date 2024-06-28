package com.banking.bankapplicationapi.repository;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Loan;
import com.banking.bankapplicationapi.exception.AccountDetailNotFound;
import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.AccountTO;
import com.banking.bankapplicationapi.model.LoanTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    Optional<Loan> findByLoanType(String string);

}
