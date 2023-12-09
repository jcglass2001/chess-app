package com.chessapp.chess.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String errorMsgKey;
    private final String errorCode;
    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_NOT_FOUND.getErrorCode();
        this.errorMsgKey = ErrorCode.USER_NOT_FOUND.getErrorMsgKey();
    }
    public UserNotFoundException(ErrorCode code) {
        this.errorCode = code.getErrorCode();
        this.errorMsgKey = code.getErrorMsgKey();
    }

}
