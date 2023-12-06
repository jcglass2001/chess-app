package com.chessapp.chess.exception;


public class GenericAlreadyExistsException extends RuntimeException {

    public GenericAlreadyExistsException(String message){
        super(message);
    }
}
