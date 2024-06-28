package com.banking.bankapplicationapi.repository;

import com.banking.bankapplicationapi.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface BankRepository extends JpaRepository<Bank,Integer> {
    Optional<Bank> findByBankName(String string);
}
