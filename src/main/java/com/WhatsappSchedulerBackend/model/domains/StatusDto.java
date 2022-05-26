package com.WhatsappSchedulerBackend.model.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {
    private String status;
    private String value;
}
