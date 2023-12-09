package com.chessapp.chess.dto.UserRequests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserProfileRequest {
    @NotBlank(message = "First Name is mandatory")
    private String firstname;
    @NotBlank(message = "Last Name is mandatory")
    private String lastname;
    @NotBlank(message = "Username should not be null")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Email(message = "Invalid email address")
    private String email;
}
