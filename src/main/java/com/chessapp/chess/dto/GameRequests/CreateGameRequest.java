package com.chessapp.chess.dto.GameRequests;

import lombok.Data;
import org.h2.engine.User;

@Data
public class CreateGameRequest {
    private String userID_1;
    private String user_ID_2;
    private Boolean isRated;
}
