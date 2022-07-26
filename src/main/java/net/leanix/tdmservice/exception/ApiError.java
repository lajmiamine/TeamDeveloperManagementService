package net.leanix.tdmservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiError {
    private String timestamp;
    private String message;
    private String details;
}