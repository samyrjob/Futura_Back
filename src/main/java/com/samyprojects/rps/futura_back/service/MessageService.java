package com.samyprojects.rps.futura_back.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samyprojects.rps.futura_back.model.Message;
import com.samyprojects.rps.futura_back.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

}
