package com.banking.bankapplicationapi.repository;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    Optional<Branch> findByBranchName(String string);
}
