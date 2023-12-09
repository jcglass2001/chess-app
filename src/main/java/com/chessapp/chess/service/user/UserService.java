package com.chessapp.chess.service.user;

import com.chessapp.chess.dto.UserRequests.CreateUserProfileRequest;
import com.chessapp.chess.dto.UserRequests.UpdateUserProfileRequest;
import com.chessapp.chess.model.user.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserProfile> getUserProfileById(long id);

    //UserProfile createNewUserProfile(CreateUserProfileRequest request) throws Exception;
    void deleteUserProfile(Long id);

    UserProfile updateUserProfile(Long id, UpdateUserProfileRequest request);

    List<UserProfile> getAllUserProfiles();
}
