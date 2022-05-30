package com.WhatsappSchedulerBackend.model.domains;

import lombok.ToString;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ToString
public class MsgErrorResponseDto extends ResponseDto{
    public String error;

    public MsgErrorResponseDto(String messageId, String sendTo, String messageBody, String scheduledTime, String status, String error){
        super(messageId, sendTo, messageBody, scheduledTime, status);
        this.error = error;
    }
}
