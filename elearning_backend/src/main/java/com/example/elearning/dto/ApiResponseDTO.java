package com.example.elearning.dto;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.Data;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ApiResponseDTO {

    String message;
    int code;
}

