package com.chessapp.chess.repo;

import com.chessapp.chess.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findUserProfileById(long uuid);

    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByEmail(String email);
}
