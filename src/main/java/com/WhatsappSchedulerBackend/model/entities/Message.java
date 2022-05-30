package com.WhatsappSchedulerBackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name="Message")
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "message_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private String messageBody;
    private LocalDateTime scheduledDateTime;
    private Long sendTo;
    private Long messageStatus;
    private String gupshupMessageId;


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
