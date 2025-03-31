package com.samyprojects.rps.futura_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samyprojects.rps.futura_back.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName (String name);
    
}
