package com.chessapp.chess.service;

import com.chessapp.chess.dto.CreateUserProfileRequest;
import com.chessapp.chess.dto.UpdateUserProfileRequest;
import com.chessapp.chess.exception.GenericAlreadyExistsException;
import com.chessapp.chess.exception.UserNotFoundException;
import com.chessapp.chess.model.user.UserProfile;
import com.chessapp.chess.repo.UserProfileRepository;
import org.modelmapper.ModelMapper;
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


    public Optional<UserProfile> getUserProfileByID(long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findUserProfileById(id);

        if(userProfile.isEmpty()){
            throw new UserNotFoundException(String.format("User not found with id: %s", id));
        }
        return userProfile;
    }

    public UserProfile createNewUserProfile(CreateUserProfileRequest request) throws Exception {

        //repo check
        Optional<UserProfile> existingUsername = userProfileRepository.findByUsername(request.getUsername());
        Optional<UserProfile> existingEmail = userProfileRepository.findByEmail(request.getEmail());

        if(existingEmail.isPresent()){
            throw new GenericAlreadyExistsException("Email is already in use.");
        }
        if(existingUsername.isPresent()){
            throw new GenericAlreadyExistsException("Username is already in use.");
        }
        //save to database
        return userProfileRepository.save(new UserProfile(request));

    }

    public void deleteUserProfile(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id: %s", id)));

        userProfileRepository.delete(userProfile);
    }
    public UserProfile updateUserProfile(Long id, UpdateUserProfileRequest request){
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id: %s", id)));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, userProfile);

        userProfileRepository.save(userProfile);


        return userProfile;
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

}
