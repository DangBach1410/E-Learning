package com.example.elearning.service;

import com.example.elearning.dto.AccountDTO;

public interface IAccountService {
    // Register and login methods should use DTOs for better encapsulation
    AccountDTO register(AccountDTO accountDTO);
    AccountDTO login(String username, String password);
}
