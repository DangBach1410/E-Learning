package com.example.elearning.repository;

import com.example.elearning.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
