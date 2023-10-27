package com.chessapp.chess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public UserProfile createNewUserProfile(String username, String password, String email) throws Exception {
        //check if user exists
        if(userProfileRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already taken.");
        }
        //save to database
        return userProfileRepository.save(new UserProfile(username,password,email));
    }

    public void deleteUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("User with id: '%s' not found", id)));
        userProfileRepository.delete(userProfile);

    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }
}
