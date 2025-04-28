package com.samyprojects.rps.futura_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyprojects.rps.futura_back.model.Utilisator;
import com.samyprojects.rps.futura_back.repository.UtilisatorRepository;

@Service
public class UtilisatorService {

    @Autowired
    private UtilisatorRepository utilisatorRepository;

    public Utilisator saveUser(Utilisator utilisator) {
        return utilisatorRepository.save(utilisator);
    }

    public List<Utilisator> getAllUsers() {
        return utilisatorRepository.findAll();
    }

    public Boolean existsByUsername(String username){
        return utilisatorRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email){
        return utilisatorRepository.existsByEmail(email);
    }

    public Optional<Utilisator> findByUsername(String username){
        return utilisatorRepository.findByUsername(username);
    }

    
    public Optional<Utilisator> findByEmail(String userEmail){
        return utilisatorRepository.findByEmail(userEmail);
    }


}