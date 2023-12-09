package com.chessapp.chess.advice;

import com.chessapp.chess.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorEntity> handleException(Exception exception) {
        return buildErrorResponse(ErrorCode.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        ErrorCode errorType = ErrorCode.ILLEGAL_ARGUMENT;
        ErrorEntity error = createError(errorType, errorMap, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ GenericException.class, UserNotFoundException.class })
    public ResponseEntity<ErrorEntity> handleGenericException(Exception exception) {
        if (exception instanceof UserNotFoundException) {
            ErrorCode errorType = ErrorCode.USER_NOT_FOUND;
            return buildErrorResponse(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ErrorCode errorType = ErrorCode.GENERIC_ERROR;
        return buildErrorResponse(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorEntity> buildErrorResponse(ErrorCode errorCode, HttpStatus status) {
        ErrorEntity error = createError(errorCode, status);
        return new ResponseEntity<>(error, status);
    }

    private ErrorEntity createError(ErrorCode errorCode, HttpStatus status) {
        return ErrorUtils.createError(errorCode.getErrorMsgKey(), errorCode.getErrorCode(), status.value());
    }

    private ErrorEntity createError(ErrorCode errorCode, Map<String, String> errorMap, HttpStatus status) {
        ErrorEntity error = createError(errorCode, status);
        error.setErrorDetails(errorMap);
        error.setTimestamp(Instant.now());
        return error;
    }
}
