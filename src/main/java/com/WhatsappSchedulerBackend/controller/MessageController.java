package com.WhatsappSchedulerBackend.controller;

import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.WhatsappSchedulerBackend.model.domains.ResponseDto;
import com.WhatsappSchedulerBackend.model.domains.StatusDto;
import com.WhatsappSchedulerBackend.constants.Constants;
import com.WhatsappSchedulerBackend.utils.RequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RequestProcessor requestProcessor;

    //build REST APIs
    //to run a basic check of the endpoints
    @GetMapping("/health/check")
    public ResponseEntity<StatusDto>healthCheckEndPoint(){
        return new ResponseEntity<>(StatusDto.builder().status(Constants.SUCCESS).value(Constants.HTTP_SUCCESS).build(), HttpStatus.OK);
    }

    //main send endpoint
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/send", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto sendMessage(RequestDto request){
        return requestProcessor.requestProcessor(request);
    }
}
