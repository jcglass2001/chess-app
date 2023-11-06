package com.chessapp.chess.service;

import com.chessapp.chess.model.UserProfile;
import com.chessapp.chess.repo.UserProfileRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getUserProfiles(){
        return userProfileRepository.findAll();
    }


    public Optional<UserProfile> getUserProfileByID(long uuid) {
        return userProfileRepository.findUserProfileById(uuid);
    }

    public UserProfile createNewUserProfile(String firstname, String lastname, String username, String password, String email) throws Exception {
        //input check
        validateUserInput(firstname, lastname, username, password, email);
        //repo check
        Optional<UserProfile> existingUsername = userProfileRepository.findByUsername(username);
        Optional<UserProfile> existingEmail = userProfileRepository.findByEmail(email);

        if(existingEmail.isPresent()){
            throw new IllegalStateException("Email already in use.");
        }
        if(existingUsername.isPresent()){
            throw new IllegalStateException("Username taken.");
        }
        //save to database
        return userProfileRepository.save(new UserProfile(firstname, lastname, username,password,email));

    }

    public void deleteUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id: '%s' not found", id)));
        userProfileRepository.delete(userProfile);

    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    private static void validateUserInput(String firstname, String lastname, String username, String password, String email) {
        if(firstname.isBlank() || lastname.isBlank() || username.isBlank() || password.isBlank() || email.isBlank()){
            throw new IllegalArgumentException("Field parameters cannot be empty.");
        }
    }
}
