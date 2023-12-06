package com.chessapp.chess.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileRequest {

    private String firstname;
    private String username;
    private String lastname;
    private String password;
    private String email;
    private String userProfileImageLink;
}
