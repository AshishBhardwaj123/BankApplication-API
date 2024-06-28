package com.banking.bankapplicationapi.repository;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByCustName(String string);
}
