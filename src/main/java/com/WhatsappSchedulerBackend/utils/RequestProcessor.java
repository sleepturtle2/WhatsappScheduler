package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.WhatsappSchedulerBackend.model.domains.ResponseDto;
import com.WhatsappSchedulerBackend.model.entities.Message;
import com.WhatsappSchedulerBackend.repositories.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Autowired
    VendorSender sender;

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

            if(message.getMessageStatus() == 5){
                sender.send(message);
                return responseProcessor.responseGenerator(message.getMessageStatus(), message);
            }
        } catch (Exception e){
            if(errorCode < 200)
                return responseProcessor.responseGenerator(errorCode, message);
            else
                return responseProcessor.responseGenerator(errorCode, request);
        }

        return responseProcessor.responseGenerator(message.getMessageStatus(), message);
    }

    private Message prepareMessage(RequestDto request){
        Message message = new Message();
        message.setAccountId(Integer.valueOf(request.getAccountId()));
        message.setMessageBody(request.getMessage());
        message.setSendTo(Integer.valueOf(request.getSendTo()));

        if (request.getScheduledTime().equals("now")) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = localDateTime.format(formatter);
            message.setScheduledDateTime(LocalDateTime.parse(formatDateTime.replace(" ", "T")));
            message.setMessageStatus(5);
        } else {
            message.setScheduledDateTime(LocalDateTime.parse(request.getScheduledTime().replace(" ", "T")));
            message.setMessageStatus(0);
        }

        return message;
    }

    private int storeMessage(Message message){
        try{
            int responseCode = messageDao.insert(message);
            message.setMessageId(messageDao.retrieveMessageId(message));
            System.out.println("responseCode = " + responseCode);

            if(responseCode == 0)
                throw exception;
        } catch(Exception e){
            return 400;
        }
        return 0;
    }
}
