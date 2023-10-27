package com.chessapp.chess.user;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class UserProfile {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;
    private String username;
    private String password;
    private String email;
    private String userProfileImageLink;

    private boolean status;

    //constructors

    public UserProfile(){

    }
    public UserProfile(long userId, String username, String password, String email, String userProfileImageLink, boolean status) {
        this.id = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userProfileImageLink = userProfileImageLink;
        this.status = status;
    }

    public UserProfile(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    //getters & setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserProfileImageLink() {
        return userProfileImageLink;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return status == that.status && Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, userProfileImageLink, status);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userProfileImageLink='" + userProfileImageLink + '\'' +
                ", status=" + status +
                '}';
    }
}
