package com.example.elearning.controller;

import com.example.elearning.dto.AccountDTO;
import com.example.elearning.dto.LoginRequestDTO;
import com.example.elearning.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.register(accountDTO));
    }
}
