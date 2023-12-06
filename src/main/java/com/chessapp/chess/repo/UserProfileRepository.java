package com.chessapp.chess.repo;

import com.chessapp.chess.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findUserProfileById(long id);

    Optional<UserProfile> findByUsername(String username);

    Optional<UserProfile> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
