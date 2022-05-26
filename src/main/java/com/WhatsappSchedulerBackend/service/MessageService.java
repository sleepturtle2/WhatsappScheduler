package com.WhatsappSchedulerBackend.service;

import com.WhatsappSchedulerBackend.model.entities.Message;

public interface MessageService {
    Message sendMessage(Message message);
}
