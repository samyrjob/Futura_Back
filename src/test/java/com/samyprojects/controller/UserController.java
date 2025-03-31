package com.samyprojects.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.samyprojects.rps.futura_back.dto.AuthResponseDTO;
import com.samyprojects.rps.futura_back.dto.LoginDTO;
import com.samyprojects.rps.futura_back.dto.RegisterDto;
import com.samyprojects.rps.futura_back.model.Role;
import com.samyprojects.rps.futura_back.model.Utilisator;
import com.samyprojects.rps.futura_back.repository.RoleRepository;
import com.samyprojects.rps.futura_back.security.JWTGenerator;
import com.samyprojects.rps.futura_back.service.UtilisatorService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {


    
    // The different services and repositories
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UtilisatorService utilisatorService;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    // Configuration of the beans 
    @Autowired
    UserController(AuthenticationManager authenticationManager, RoleRepository roleRepository, UtilisatorService utilisatorService, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator){
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.utilisatorService = utilisatorService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterDto registerDTO) {
        // Validate the RegisterDTO object
        if (registerDTO.getUsername() == null || registerDTO.getUsername().isEmpty() ||
            registerDTO.getPassword() == null || registerDTO.getPassword().isEmpty() ||
            registerDTO.getEmail() == null || registerDTO.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Username, email, and password cannot be empty."));
        }

        // Check if the username is already taken
        if (utilisatorService.existsByUsername(registerDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Username is already taken."));
        }

        // Check if the email is already taken (if applicable)
        if (utilisatorService.existsByEmail(registerDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email is already registered."));
        }

        // Create a new Utilisator object from the RegisterDTO
        Utilisator user = new Utilisator();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));


        // put the roles

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        

        // Save the user and check if the save operation was successful
        try {
            utilisatorService.saveUser(user);
            return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
        } catch (Exception e) {
            // If there is an issue with saving the user, return 500 status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error registering user."));
        }
    }



    @GetMapping("/utilisators")
    public List<Utilisator> getAllUtilisateurs() {
        return utilisatorService.getAllUsers();
    }




    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
    try {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // Set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a JWT token
        String token = jwtGenerator.generateToken(authentication);

        // Return the token in the response
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    } catch (AuthenticationException e) {
        // Log the error and return a 401 Unauthorized response
        System.err.println("Authentication failed: " + e.getMessage());
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}





    

}