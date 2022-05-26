package com.WhatsappSchedulerBackend.controller;

import com.WhatsappSchedulerBackend.model.domains.StatusDto;
import com.WhatsappSchedulerBackend.model.entities.Message;
import com.WhatsappSchedulerBackend.service.MessageService;
import com.WhatsappSchedulerBackend.utils.Constants;
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
    @GetMapping("/health/check")
    public ResponseEntity<StatusDto>healthCheckEndPoint(){
        return new ResponseEntity<>(StatusDto.builder().status(Constants.SUCCESS).value(Constants.HTTP_SUCCESS).build(), HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Message>sendMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(messageService.sendMessage(message), HttpStatus.CREATED);
    }
}
