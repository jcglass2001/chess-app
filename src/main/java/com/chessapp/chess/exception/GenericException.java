package com.chessapp.chess.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private final String errMessageKey;
    private final String errCode;

    public GenericException(ErrorCode errorCode){
        this.errMessageKey = errorCode.getErrorMsgKey();
        this.errCode = errorCode.getErrorCode();
    }
}
