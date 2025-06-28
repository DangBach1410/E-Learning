package com.example.elearning.service;

import com.example.elearning.dto.AccountDTO;
import com.example.elearning.model.Account;
import com.example.elearning.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import com.example.elearning.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;


    @Override
    public AccountDTO register(AccountDTO accountDTO) {
        if (accountRepository.existsByUsername(accountDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Account account = modelMapper.map(accountDTO, Account.class);
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        return mapToDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty() || !passwordEncoder.matches(password, optionalAccount.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        AccountDTO dto = mapToDTO(optionalAccount.get());
        dto.setToken(jwtUtil.generateToken(username));
        return dto;
    }

    private AccountDTO mapToDTO(Account account) {
        AccountDTO dto = modelMapper.map(account, AccountDTO.class);
        dto.setPassword(null);
        return dto;
    }
}
