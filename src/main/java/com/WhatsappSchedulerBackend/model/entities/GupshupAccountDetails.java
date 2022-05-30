package com.WhatsappSchedulerBackend.model.entities;

import lombok.*;

@Data
@ToString

public class GupshupAccountDetails extends Entities{
    private int accountId;
    private String gupshupApiKey;
}
