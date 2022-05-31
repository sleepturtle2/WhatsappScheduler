package com.WhatsappSchedulerBackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class Message extends Entities {
    private int messageId;
    private int accountId;
    private String messageBody;
    private Timestamp scheduledDateTime;
    private String sendTo;
    private int messageStatus;


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
