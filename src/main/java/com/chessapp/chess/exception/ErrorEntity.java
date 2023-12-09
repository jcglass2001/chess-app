package com.chessapp.chess.exception;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class ErrorEntity {
    private String errorCode;
    private String errorMessage;
    private Map<String,String> errorDetails;
    private Integer status;
    private Instant timestamp;
}
