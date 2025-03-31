package com.samyprojects.rps.futura_back.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samyprojects.rps.futura_back.model.Utilisator;

import com.samyprojects.rps.futura_back.dto.AuthResponseStatusDTO;
import com.samyprojects.rps.futura_back.security.JWTGenerator;
import com.samyprojects.rps.futura_back.service.UtilisatorService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JWTGenerator jwtUtil; // Utility to validate JWT

    @Autowired
    private UtilisatorService userService; // Service to fetch user data

    @GetMapping("/validate-token-user")
    public ResponseEntity<?> validateToken(@CookieValue("JWT_token") String token) {
        try {

            // Validate the token
            if (jwtUtil.validateToken(token)) {
                // Fetch user data
                String username = jwtUtil.getUsernameFromJWT(token);
                Optional<Utilisator> userOptional = userService.findByUsername(username);

                // Return user data
                if (userOptional.isPresent()){
                    Utilisator user = userOptional.get();
                    return ResponseEntity.ok(new AuthResponseStatusDTO(user, true));
                }
                else {
                    return ResponseEntity.ok(new AuthResponseStatusDTO(null, false));
                }
            } else {
                return ResponseEntity.ok(new AuthResponseStatusDTO(null, false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

}