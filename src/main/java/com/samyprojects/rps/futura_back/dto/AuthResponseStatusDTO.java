package com.samyprojects.rps.futura_back.dto;

import com.samyprojects.rps.futura_back.model.Utilisator;

import lombok.Data;


@Data
public class AuthResponseStatusDTO {
    private Utilisator user;
    private boolean authenticated;

    
    public AuthResponseStatusDTO(Utilisator user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }

    
    
}
