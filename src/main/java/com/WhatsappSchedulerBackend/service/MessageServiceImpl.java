package com.WhatsappSchedulerBackend.service;

import com.WhatsappSchedulerBackend.model.Message;
import com.WhatsappSchedulerBackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}
