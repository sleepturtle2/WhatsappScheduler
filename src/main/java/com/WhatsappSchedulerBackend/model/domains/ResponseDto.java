package com.WhatsappSchedulerBackend.model.domains;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ResponseDto {
    public String messageId;
    public String sendTo;
    public String messageBody;
    public String scheduledTime;
    public String status;


}
