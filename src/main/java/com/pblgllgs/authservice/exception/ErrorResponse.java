package com.pblgllgs.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private String path;
    private String exceptionName;
    private String message;
    private int status;
    private LocalDateTime localDateTime;
}