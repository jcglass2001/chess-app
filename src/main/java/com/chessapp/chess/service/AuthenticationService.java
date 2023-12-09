package com.chessapp.chess.service;

import com.chessapp.chess.dto.AuthenticationRequest;
import com.chessapp.chess.dto.AuthenticationResponse;
import com.chessapp.chess.dto.RegisterRequest;
import com.chessapp.chess.exception.ErrorCode;
import com.chessapp.chess.exception.GenericException;
import com.chessapp.chess.model.user.Role;
import com.chessapp.chess.model.user.UserProfile;
import com.chessapp.chess.repo.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse registerUser(RegisterRequest request) {

        var user = UserProfile.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userProfileRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userProfileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GenericException(ErrorCode.GENERIC_ERROR));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
