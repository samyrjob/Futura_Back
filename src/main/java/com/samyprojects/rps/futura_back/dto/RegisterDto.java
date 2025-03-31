package com.samyprojects.rps.futura_back.dto;

import lombok.Data;

@Data
public class RegisterDto {
    String username;
    String email;
    String password;
}