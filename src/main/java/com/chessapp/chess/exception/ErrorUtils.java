package com.chessapp.chess.exception;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class ErrorUtils {
    public static ErrorEntity createError(String errorMsgKey, String errorCode, Map<String,String> errorDetails, Integer httpStatusCode) {
        ErrorEntity error = new ErrorEntity();

        error.setErrorMessage(errorMsgKey);
        error.setStatus(httpStatusCode);
        error.setErrorCode(errorCode);
        error.setErrorDetails(errorDetails);
        return error;
    }
    public static ErrorEntity createError(String errorMsgKey, String errorCode, Integer httpStatus){
        return createError(errorMsgKey,errorCode,null,httpStatus);
    }
}
