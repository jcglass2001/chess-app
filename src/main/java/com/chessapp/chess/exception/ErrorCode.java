package com.chessapp.chess.exception;


import lombok.Getter;

@Getter
public enum ErrorCode  {

    GENERIC_ERROR("C-001","The system is unable to complete the request"),
    GENERIC_ALREADY_EXISTS("C-002","Already Exists"),
    ITEM_NOT_FOUND("C-003","Requested Item not found"),
    RESOURCE_NOT_FOUND("C-004", "Requested Resource not found"),
    ILLEGAL_ARGUMENT("C-005","Invalid data passed"),
    USER_NOT_FOUND("C-006","Requested user not found");


    private final String errorCode;
    private final String errorMsgKey;

    private ErrorCode(final String errorCode, final String errorMsgKey) {
        this.errorCode = errorCode;
        this.errorMsgKey = errorMsgKey;
    }
}
