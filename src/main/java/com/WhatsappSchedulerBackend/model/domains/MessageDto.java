package com.WhatsappSchedulerBackend.model.domains;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long messageId;
    private String messageBody;
    private LocalDateTime scheduledDateTime;
    private Long receiverPhoneNumber;
    private Long clientId;
    private LocalDateTime creationDateTime;
    private Long messageStatus;
    private LocalDateTime submittedDateTime;
    private String gupshupMessageId;
}
