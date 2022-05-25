package com.WhatsappSchedulerBackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Message")
public class Message {
    @Id
    @Column(name = "message_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String messageBody;
    private LocalDateTime scheduledDateTime;
    private Long receiverPhoneNumber;
    private Long clientId;
    private LocalDateTime creationDateTime;
    private Integer messageStatus;
    private LocalDateTime submittedDateTime;
    private String gupshupMessageId;


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
