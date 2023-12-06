package com.chessapp.chess.exception;

public class GenericItemNotFoundException extends RuntimeException{
    public GenericItemNotFoundException(String message){
        super(message);
    }
}
