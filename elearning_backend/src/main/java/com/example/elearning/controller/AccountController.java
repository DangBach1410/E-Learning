package com.example.elearning.controller;

import com.example.elearning.dto.AccountDTO;
import com.example.elearning.dto.LoginRequestDTO;
import com.example.elearning.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest.getUsername(), loginRequest.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestBody @Valid AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.register(accountDTO));
    }
}
