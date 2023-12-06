package com.chessapp.chess.service;

import com.chessapp.chess.dto.CreateUserProfileRequest;
import com.chessapp.chess.model.user.UserProfile;
import com.chessapp.chess.repo.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserProfileRepository userProfileRepository;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userProfileRepository);
    }

    @Test
    @Disabled
    void getUserProfileByID() {
    }

    @Test
    void canCreateNewUserProfile() throws Exception {
        //given
        CreateUserProfileRequest createUserProfileRequest = new CreateUserProfileRequest(
                "John",
                "Doe",
                "jd420",
                "jd2023",
                "jdoe2023@mail.com"
        );
        UserProfile expectedUser = new UserProfile(createUserProfileRequest);
        //when
        underTest.createNewUserProfile(createUserProfileRequest);
        //then
        ArgumentCaptor<UserProfile> userProfileRequestArgumentCaptor =
                ArgumentCaptor.forClass(UserProfile.class);

        verify(userProfileRepository).save(userProfileRequestArgumentCaptor.capture());

        UserProfile capturedUser = userProfileRequestArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(expectedUser);
    }

    @Test
    @Disabled
    void deleteUserProfile() {
    }

    @Test
    void canGetAllUserProfiles() {
        //when
        underTest.getAllUserProfiles();
        //then
        verify(userProfileRepository).findAll();
    }
}