package com.WhatsappSchedulerBackend.utils;

import com.WhatsappSchedulerBackend.constants.ErrorCodes;
import com.WhatsappSchedulerBackend.model.domains.MsgErrorResponseDto;
import com.WhatsappSchedulerBackend.model.domains.RequestDto;
import com.WhatsappSchedulerBackend.model.domains.ResponseDto;
import com.WhatsappSchedulerBackend.model.entities.Message;

public class ResponseProcessor {
    public ResponseDto responseGenerator(int errorCode, RequestDto request){
        String error = ErrorCodes.codeToString(errorCode);
        MsgErrorResponseDto response = new MsgErrorResponseDto("No message ID", request.getSendTo(), request.getMessage(), request.getScheduledTime(), "ERROR", error);
        System.out.println(response);
        return response;
    }

    public ResponseDto responseGenerator(int errorCode, Message message){
        String error = ErrorCodes.codeToString(errorCode);
        ResponseDto response = null;

        if(errorCode < 10){
            response = new ResponseDto(Long.toString(message.getMessageId()), message.get)
        }
    }
}
