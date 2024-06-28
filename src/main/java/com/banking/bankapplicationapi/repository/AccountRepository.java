package com.banking.bankapplicationapi.repository;

import com.banking.bankapplicationapi.entity.Account;
import com.banking.bankapplicationapi.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Optional<Account> findByAccountType(String string);
}
