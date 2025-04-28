package com.samyprojects.rps.futura_back.dto;



import lombok.Data;


@Data
public class AuthResponseStatusDTO {
    private UtilisatorDTO user;
    private boolean authenticated;

    

    public AuthResponseStatusDTO(UtilisatorDTO user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }

    
    
}
