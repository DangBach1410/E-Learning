package com.example.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String dateOfBirth;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String token;
}
