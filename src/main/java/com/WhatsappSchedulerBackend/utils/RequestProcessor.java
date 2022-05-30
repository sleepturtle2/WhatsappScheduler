package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.WhatsappSchedulerBackend.model.domains.ResponseDto;
import com.WhatsappSchedulerBackend.model.entities.Message;
import com.WhatsappSchedulerBackend.repositories.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessor {
    Exception exception;

    @Autowired
    Validator validator;
    @Autowired
    Authenticator authenticator;
    @Autowired
    ResponseProcessor responseProcessor;
    @Autowired
    MessageDao messageDao;
    //@Autowired
    //VendorSendor sender;

    int errorCode = -1;

    public ResponseDto requestProcessor(RequestDto request){
        Message message = null;

        try{
            errorCode = authenticator.verifyClient(request);
            System.out.println("Authentication Error Code: " + errorCode);
            if(errorCode != 0)
                throw exception;

            System.out.println(request);
            errorCode = validator.messageIsValid(request);

            if(errorCode != 0)
                throw exception;

            message = prepareMessage(request);
            errorCode = storeMessage(message);

            if(message.getStatus() == 5){
                sender.send(message);
                return responseProcessor.responseGenerator(message.getStatus(), message);
            }
        } catch (Exception e){
            if(errorCode......)
                return responseGenerator.responseGenerator(errorCode, message);
            else
                return responseProcessor.responseGenerator(errorCode, request);
        }

        return responseProcessor.responseGenerator(message.getStatus(), message);
    }

    private Message prepareMessage(RequestDto request){
        Message message = new Message();
        message.set
    }
}
