package com.samyprojects.rps.futura_back.dto;

import com.samyprojects.rps.futura_back.model.Utilisator;

import lombok.Data;

@Data
public class UtilisatorDTO {
   
    String username;
    String email;  

    public UtilisatorDTO(Utilisator utilisator) {
        this.username = utilisator.getName();
        this.email = utilisator.getEmail();
    }
}
