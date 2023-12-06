package com.chessapp.chess.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChessGame {
    private Long id;
    //private Player whitePlayer;
    //private Player blackPlayer;
    private List<?> moves;
    private GameState state;
}
