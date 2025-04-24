package com.samyprojects.rps.futura_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.samyprojects.rps.futura_back.model.Utilisator;

@Repository
public interface UserRepository extends JpaRepository<Utilisator, Integer>{


    Optional<Utilisator> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<Utilisator> findByEmail(String email);

    
}
