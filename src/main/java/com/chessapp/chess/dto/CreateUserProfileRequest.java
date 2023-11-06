package com.chessapp.chess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserProfileRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
}
