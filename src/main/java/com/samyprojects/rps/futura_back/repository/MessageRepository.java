package com.samyprojects.rps.futura_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

// import org.springframework.data.repository.CrudRepository;

import com.samyprojects.rps.futura_back.model.Message;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    
    
}
