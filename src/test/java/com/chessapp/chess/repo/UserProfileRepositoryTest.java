package com.chessapp.chess.repo;

import com.chessapp.chess.model.user.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserProfileRepositoryTest {
    @Autowired
    private UserProfileRepository underTest;
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void shouldFindByUsername() {
        //given
        String username = "jd420";
        UserProfile userProfile = new UserProfile(
                "John",
                "Doe",
                username,
                "jd2023",
                "jdo2023@mail.com"
        );
        underTest.save(userProfile);
        //when
        Optional<UserProfile> user = underTest.findByUsername(username);

        //then
        assertThat(user).isPresent();
    }

    @Test
    void shouldFindByEmail() {
        //given
        String email = "jdo2023@mail.com";
        UserProfile userProfile = new UserProfile(
                "John",
                "Doe",
                "jd420",
                "jd2023",
                email
        );
        underTest.save(userProfile);
        //when
        Optional<UserProfile> user = underTest.findByEmail(email);

        //then
        assertThat(user).isPresent();
    }

    @Test
    void shouldExistByEmail() {
        //given
        String email = "jdo2023@mail.com";
        UserProfile userProfile = new UserProfile(
                "John",
                "Doe",
                "jd420",
                "jd2023",
                email
        );
        underTest.save(userProfile);
        //when
        boolean exists = underTest.existsByEmail(email);

        //then
        assertThat(exists).isTrue();
    }
    @Test
    void shouldNotExistByEmail() {
        //given
        String email = "jdo2023@mail.com";
        //when
        boolean exists = underTest.existsByEmail(email);
        //then
        assertThat(exists).isFalse();
    }

    @Test
    void shouldExistByUsername() {
        //given
        String username = "jd420";
        UserProfile userProfile = new UserProfile(
                "John",
                "Doe",
                username,
                "jd2023",
                "jdo2023@mail.com"
        );
        underTest.save(userProfile);
        //when
        boolean exists = underTest.existsByUsername(username);

        //then
        assertThat(exists).isTrue();
    }
    @Test
    void shouldNotExistByUsername() {
        //given
        String username = "jd420";
        //when
        boolean exists = underTest.existsByUsername(username);
        //then
        assertThat(exists).isFalse();
    }
}