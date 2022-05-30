package com.WhatsappSchedulerBackend.model.domains;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private String accountId;
    private String clientId;
    private String uToken;
    private String message;
    private String scheduledTime;
    private String sendTo;
}
