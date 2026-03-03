package com.alwriter.ContentGeneratorAgent.repository;

import com.alwriter.ContentGeneratorAgent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.preferences = :preferences WHERE u.id = :userId")
    int updatePreferenceByUserId(UUID userId, String preferences);
}