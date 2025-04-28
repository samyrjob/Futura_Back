package com.samyprojects.rps.futura_back.dto;

import com.samyprojects.rps.futura_back.model.Utilisator;

import lombok.Data;

@Data
public class AuthResponseDTO {
    
    private String accessToken;
    private String tokenType = "Bearer ";
    private UtilisatorDTO user;

    public AuthResponseDTO(String accessToken, UtilisatorDTO user) {
        this.accessToken = accessToken;
        this.user = user;
    }
    
}
