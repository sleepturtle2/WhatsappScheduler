package com.WhatsappSchedulerBackend.controller;

import com.WhatsappSchedulerBackend.model.Message;
import com.WhatsappSchedulerBackend.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        super();
        this.messageService = messageService;
    }

    //build REST APIs
    @PostMapping("send")
    public ResponseEntity<Message>sendMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(messageService.sendMessage(message), HttpStatus.CREATED);
    }
}
