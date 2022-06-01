package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.constants.ErrorCodes;
import com.WhatsappSchedulerBackend.model.domains.MsgErrorResponseDto;
import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.WhatsappSchedulerBackend.model.domains.ResponseDto;
import com.WhatsappSchedulerBackend.model.entities.Message;
import org.springframework.stereotype.Service;

@Service
public class ResponseProcessor {
    public ResponseDto responseGenerator(int errorCode, RequestDto request){
        String error = ErrorCodes.codeToString(errorCode);
        MsgErrorResponseDto response = new MsgErrorResponseDto("No message ID", request.getSendTo(), request.getMessage(), request.getScheduledTime(), "ERROR", error);
        System.out.println(response);
        return response;
    }

    public ResponseDto responseGenerator(int errorCode, Message message){

        String error = ErrorCodes.codeToString(errorCode);
        //insert if here
        System.out.println(message);
        ResponseDto response = new ResponseDto(Integer.toString(message.getMessageId()), message.getSendTo(), message.getMessageBody(), message.getScheduledDateTime().toString(), Integer.toString(errorCode));

        System.out.println(response);
        return response;
    }
}
