package com.chessapp.chess.model.user;

import com.chessapp.chess.dto.CreateUserProfileRequest;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class UserProfile {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String userProfileImageLink;
    private UserStatus status;

    public UserProfile(String firstname, String lastname, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public UserProfile(CreateUserProfileRequest request) {
        this.firstname = request.getFirstname();
        this.lastname = request.getLastname();
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.email = request.getEmail();
        this.status = UserStatus.OFFLINE;
    }
}
