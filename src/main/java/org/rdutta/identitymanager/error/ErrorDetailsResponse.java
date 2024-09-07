package org.rdutta.identitymanager.error;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDetailsResponse {
    private int errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;
}
